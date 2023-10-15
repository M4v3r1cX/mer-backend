package com.bsodsoftware.merbackend.services;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;
import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.Propiedad;
import com.bsodsoftware.merbackend.jpa.entities.Red;
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
	
	private void save(ActividadMer entity) {
		actividadMerRepository.saveAndFlush(entity);
	}
	
	public void guardarActividad(ActividadMerDTO merdto, Long idUsuario) {
		ActividadMer amer = null;
		if (merdto.getId() != null && !merdto.getId().isEmpty()) {
			amer = actividadMerRepository.getReferenceById(Long.valueOf(merdto.getId()));
		} else {
			amer= new ActividadMer();
		}
		amer.setDescripcionActividad(merdto.getDescripcionActividad());
		amer.setIdUsuarioCarga(idUsuario);
		//amer.setImagenReferencia(merdto.getImagenReferencia());
		amer.setLinkReferencia(merdto.getLinkReferencia());
		amer.setUbicacionEnLibro(merdto.getUbicacionEnLibro());
		amer.setNombre(merdto.getNombre());
		amer.setFechaHoraCreacion(new Date());
		
		String pathImagen = guardarImagen(merdto.getImagenReferencia());
		amer.setImagenReferencia(pathImagen);
		
		Libro l = libroService.findById(Long.valueOf(merdto.getIdLibro()));
		if (l != null) {
			amer.setLibro(l);
		}
		
		TareaMatematica tm = tmService.getTm(Long.valueOf(merdto.getIdTm()));
		if (tm != null) {
			amer.setTareaMatematica(tm);
		}
		
		save(amer);
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
				for (Red r : a.getTareaMatematica().getObjetivoAprendizajeHijo().getRedes()) {
					ldto.addRed(r.getNombre());
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
		}
		return ret;
	}
	
	private String guardarImagen(String imagenReferencia) {
		String path = null;
		if (imagenReferencia != null && !imagenReferencia.isEmpty()) {
			Propiedad prop = propiedadService.getPropiedad("PATH_IMAGENES", "C:\\images\\");
			path = prop.getValue();
			UUID uuid = UUID.randomUUID();
			path += uuid.toString();
			
			String partes[] = imagenReferencia.split(",");
			byte[] data = Base64.getDecoder().decode(partes[1]);
			try (OutputStream stream = new FileOutputStream(path)) {
			    stream.write(data);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return path;
	}
}
