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
		ActividadMer amer = new ActividadMer();
		amer.setDescripcionActividad(merdto.getDescripcionActividad());
		amer.setIdLibro(merdto.getIdLibro());
		amer.setIdNivel(merdto.getIdNivel());
		amer.setIdRed(merdto.getIdRed());
		amer.setIdUsuarioCarga(idUsuario);
		amer.setImagenReferencia(merdto.getImagenReferencia());
		amer.setLinkReferencia(merdto.getLinkReferencia());
		amer.setTextoCajaOaCapa2(merdto.getTextoCajaOaCapa2());
		amer.setTextoCajaTmCapa2(merdto.getTextoCajaTmCapa2());
		amer.setUbicacionEnLibro(merdto.getUbicacionEnLibro());
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
}
