package com.bsodsoftware.merbackend.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.DtoInstantiatingConverter;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;
import com.bsodsoftware.merbackend.jpa.entities.Auditoria;
import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.Propiedad;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;
import com.bsodsoftware.merbackend.jpa.repository.ActividadMerRepository;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;

@Service
public class ActividadMerService {

	@Autowired
	private ActividadMerRepository actividadMerRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private TareaMatematicaService tmService;
	
	@Autowired
	private PropiedadService propiedadService;
	
	@Autowired
	private AuditoriaService auditoriaService;
	
	private ActividadMer save(ActividadMer entity) {
		return actividadMerRepository.saveAndFlush(entity);
	}
	
	public void guardarActividad(ActividadMerDTO merdto, Long idUsuario) {
		Auditoria.ACCION accion = null;
		ActividadMer amer = null;
		if (merdto.getId() != null && !merdto.getId().isEmpty()) {
			amer = actividadMerRepository.getReferenceById(Long.valueOf(merdto.getId()));
			accion = Auditoria.ACCION.ACTIVIDAD_EDITAR;
		} else {
			amer= new ActividadMer();
			accion = Auditoria.ACCION.ACTIVIDAD_CREAR;
		}
		amer.setDescripcionActividad(merdto.getDescripcionActividad());
		amer.setIdUsuarioCarga(idUsuario);
		//amer.setImagenReferencia(merdto.getImagenReferencia());
		amer.setLinkReferencia(merdto.getLinkReferencia());
		amer.setUbicacionEnLibro(merdto.getUbicacionEnLibro());
		amer.setNombre(merdto.getNombre());
		amer.setFechaHoraCreacion(new Date());
		
		String pathImagen = guardarImagen(merdto.getImagenReferencia(), amer.getImagenReferencia());
		amer.setImagenReferencia(pathImagen);
		
		Libro l = libroService.findById(Long.valueOf(merdto.getIdLibro()));
		if (l != null) {
			amer.setLibro(l);
		}
		
		TareaMatematica tm = tmService.getTm(Long.valueOf(merdto.getIdTm()));
		if (tm != null) {
			amer.setTareaMatematica(tm);
		}
		
		amer = save(amer);
		
		auditoriaService.guardarAccion(accion, idUsuario, amer.getId());
	}

	public void delete(Long id) {
		actividadMerRepository.deleteById(id);
	}
	
	public List<ActividadMerListaDto> getActividades() {
		List<ActividadMerListaDto> ret = null;
		List<ActividadMer> act = actividadMerRepository.findAll();
		if (act != null && !act.isEmpty()) {
			ret = new ArrayList<ActividadMerListaDto>();
			for (ActividadMer a : act) {
				ActividadMerListaDto ldto = new ActividadMerListaDto();
				ldto.setId(a.getId());
				ldto.setNombre(a.getNombre());
				ldto.setLibro(a.getLibro().getNombre());
				ldto.setTm("TM-" + a.getTareaMatematica().getId());
				ldto.setOa(a.getTareaMatematica().getObjetivoAprendizajeHijo().getObjetivoAprendizaje().getNombre());
				for (Nivel n : a.getTareaMatematica().getObjetivoAprendizajeHijo().getNiveles()) {
					ldto.addNivel(n.getNombre());
				}
				for (SubcategoriaRed r : a.getTareaMatematica().getObjetivoAprendizajeHijo().getSubcategorias()) {
					ldto.addRed(r.getRed().getNombre());
				}
				ret.add(ldto);
			}
		}
		
		return ret;
	}
	
	public List<LibroRedDTO> getLibros() {
		List<LibroRedDTO> libs = null;
		List<Libro> libros = libroService.findAll();
		if (libros != null && !libros.isEmpty()) {
			libs = new ArrayList<LibroRedDTO>();
			for (Libro lib : libros) {
				LibroRedDTO l = new LibroRedDTO();
				l.setId(lib.getId());
				l.setNombre(lib.getNombre());
				libs.add(l);
			}
		}
		
		return libs;
	}

	public ActividadMerDTO getActividad(Long id) {
		ActividadMerDTO ret = null;
		ActividadMer amer = actividadMerRepository.getReferenceById(id);
		if (amer != null) {
			ret = new ActividadMerDTO();
			ret.setDescripcionActividad(amer.getDescripcionActividad());
			ret.setIdLibro(amer.getLibro().getId()  + "");
			ret.setIdUsuarioCarga(amer.getIdUsuarioCarga() + "");
			ret.setLinkReferencia(amer.getLinkReferencia());
			ret.setNombre(amer.getNombre());
			ret.setUbicacionEnLibro(amer.getUbicacionEnLibro());
			ret.setId(amer.getId() + "");
			ret.setIdTm(amer.getTareaMatematica().getId() + "");
			ret.setImagenReferencia(getImagenB64(amer.getImagenReferencia()));
		}
		return ret;
	}
	
	private String getImagenB64(String imagenReferencia) {
		String ret = null;
		if (imagenReferencia != null && !imagenReferencia.isEmpty()) {
			try {
				byte[] fileContent = FileUtils.readFileToByteArray(new File(imagenReferencia));
				ret = Base64.getEncoder().encodeToString(fileContent);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return ret;
	}

	private String guardarImagen(String imagenB64, String imagenReferenciaActual) {
		String path = null;
		
		if (imagenB64 != null && !imagenB64.isEmpty()) {
			if (imagenReferenciaActual != null && !imagenReferenciaActual.isEmpty()) {
				path = imagenReferenciaActual;
			} else {
				Propiedad prop = propiedadService.getPropiedad("PATH_IMAGENES", "C:\\images\\");
				path = prop.getValue();
				UUID uuid = UUID.randomUUID();
				path += uuid.toString();
			}
			
			String partes[] = imagenB64.split(",");
			byte[] data = Base64.getDecoder().decode(partes[1]);
			try (OutputStream stream = new FileOutputStream(path)) {
			    stream.write(data);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return path;
	}
	
	public List<ActividadMerDTO> getActividadesByIdTareaMatematica(Long id) {
		List<ActividadMerDTO> ret = null;
		List<ActividadMer> act = actividadMerRepository.findActividadesByTareaMatematica(id);
		if (act != null && !act.isEmpty()) {
			ret = new ArrayList<ActividadMerDTO>();
			for (ActividadMer a : act) {
				ActividadMerDTO dto = new ActividadMerDTO();
				dto.setDescripcionActividad(a.getDescripcionActividad());
				dto.setId(a.getId() + "");
				dto.setIdLibro(a.getLibro().getId() + "");
				dto.setImagenReferencia("data:image/jpg;base64," + getImagenB64(a.getImagenReferencia()));
				dto.setLinkReferencia(a.getLinkReferencia());
				dto.setNombre(a.getNombre());
				dto.setUbicacionEnLibro(a.getUbicacionEnLibro());
				ret.add(dto);
			}
		}
		
		return ret;
	}
}
