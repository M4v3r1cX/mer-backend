package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;
import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.ActividadMerRepository;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.LibroRedResponse;

@Service
public class ActividadMerService {

	@Autowired
	private ActividadMerRepository actividadMerRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private RedService redService;
	
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
		amer.setIdLibro(Long.valueOf(merdto.getIdLibro()));
		amer.setIdNivel(Long.valueOf(merdto.getIdNivel()));
		amer.setIdRed(Long.valueOf(merdto.getIdRed()));
		amer.setIdUsuarioCarga(idUsuario);
		amer.setImagenReferencia(merdto.getImagenReferencia());
		amer.setLinkReferencia(merdto.getLinkReferencia());
		amer.setTextoCajaOaCapa2(merdto.getTextoCajaOaCapa2());
		amer.setTextoCajaTmCapa2(merdto.getTextoCajaTmCapa2());
		amer.setUbicacionEnLibro(merdto.getUbicacionEnLibro());
		amer.setNombre(merdto.getNombre());
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
				ldto.setNivel(a.getIdNivel() + "º");
				Libro l = libroService.findById(a.getIdLibro());
				if (l != null) {
					ldto.setLibro(l.getNombre());
				}
				Red r = redService.findById(a.getIdRed());
				if (r != null) {
					ldto.setRed(r.getNombre());
				}
				ret.add(ldto);
			}
		}
		
		return ret;
	}
	
	public LibroRedResponse getLibrosYRedes() {
		LibroRedResponse ret = null;
		List<LibroRedDTO> reds = null;
		List<LibroRedDTO> libs = null;
		List<Red> redes = redService.findAll();
		if (redes != null && !redes.isEmpty()) {
			reds = new ArrayList<LibroRedDTO>();
			for (Red r : redes) {
				LibroRedDTO l = new LibroRedDTO();
				l.setId(r.getId());
				l.setNombre(r.getNombre());
				reds.add(l);
			}
		}
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
		
		if (reds != null || libs != null) {
			ret = new LibroRedResponse();
			ret.setLibros(libs);
			ret.setRedes(reds);
		}
		
		
		return ret;
	}

	public ActividadMerDTO getActividad(Long id) {
		ActividadMerDTO ret = null;
		ActividadMer amer = actividadMerRepository.getReferenceById(id);
		if (amer != null) {
			ret = new ActividadMerDTO();
			ret.setDescripcionActividad(amer.getDescripcionActividad());
			ret.setIdLibro(amer.getIdLibro()  + "");
			ret.setIdNivel(amer.getIdNivel() + "");
			ret.setIdRed(amer.getIdRed() + "");
			ret.setIdUsuarioCarga(amer.getIdUsuarioCarga() + "");
			ret.setLinkReferencia(amer.getLinkReferencia());
			ret.setNombre(amer.getNombre());
			ret.setTextoCajaOaCapa2(amer.getTextoCajaOaCapa2());
			ret.setTextoCajaTmCapa2(amer.getTextoCajaTmCapa2());
			ret.setUbicacionEnLibro(amer.getUbicacionEnLibro());
			ret.setId(amer.getId() + "");}
		return ret;
	}
}
