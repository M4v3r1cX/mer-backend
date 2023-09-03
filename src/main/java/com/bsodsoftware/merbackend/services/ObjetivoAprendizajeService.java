package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAprendizajeRepository;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.LibroRedResponse;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.RedResponse;

@Service
public class ObjetivoAprendizajeService {
	
	@Autowired
	private RedService redService;
	
	@Autowired
	private NivelService nivelService;

	@Autowired
	private ObjetivoAprendizajeRepository oaRepository;
	
	private void save(ObjetivoAprendizaje entity) {
		oaRepository.saveAndFlush(entity);
	}
	
	public void guardarOa(OaDTO oadto, Long idUsuario) {
		ObjetivoAprendizaje oa = null;
		if (oadto.getId() != null && !oadto.getId().isEmpty()) {
			oa = oaRepository.getReferenceById(Long.valueOf(oadto.getId()));
		} else {
			oa = new ObjetivoAprendizaje();
		}
		oa.setDescripcion(oadto.getDescripcion());
		oa.setIdUsuario(idUsuario);
		oa.setNombre(oadto.getNombre());
		oa.setPriorizado(oadto.getPrioridad());
		for (String r : oadto.getRedes()) {
			Red red = redService.findById(Long.valueOf(r));
			if (red != null) {
				oa.addRed(red);
			}
		}
		for (String n : oadto.getNiveles()) {
			Nivel nivel = nivelService.findById(Long.valueOf(n));
			if (nivel != null) {
				oa.addNivel(nivel);
			}
		}
		
		if (oadto.getHijos() != null && !oadto.getHijos().isEmpty()) {
			for (OaHijoDTO oahijodto : oadto.getHijos()) {
				ObjetivoAprendizajeHijo oahijo = new ObjetivoAprendizajeHijo();
				oahijo.setDescripcion(oahijodto.getDescripcion());
				oahijo.setObjetivoAprendizaje(oa);
				for (String r : oahijodto.getRedes()) {
					Red red = redService.findById(Long.valueOf(r));
					if (red != null) {
						oahijo.addRed(red);
					}
				}
				for (String n : oahijodto.getNiveles()) {
					Nivel nivel = nivelService.findById(Long.valueOf(n));
					if (nivel != null) {
						oahijo.addNivel(nivel);
					}
				}
				oa.addHijo(oahijo);
			}
		}
		save(oa);
	}
	
	public void delete(Long id) {
		oaRepository.deleteById(id);
	}
	
	public List<OaDTO> getOas() {
		List<OaDTO> ret = null;
		List<ObjetivoAprendizaje> oas = oaRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaDTO>();
			for (ObjetivoAprendizaje oa : oas) {
				OaDTO oadto = new OaDTO();
				oadto.setId(oa.getId() + "");
				for (Red r : oa.getRedes()) {
					oadto.addRed(r.getNombre());
				}
				for (Nivel n : oa.getNiveles()) {
					oadto.addNivel(n.getNombre());
				}
				oadto.setNombre(oa.getNombre());
				oadto.setDescripcion(oa.getDescripcion());
				oadto.setPrioridad(oa.isPriorizado());
				ret.add(oadto);
			}
		}
		
		return ret;
	}
	
	public OaDTO getOa(Long id) {
		OaDTO ret = null;
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null) {
			ret = new OaDTO();
			ret.setDescripcion(oa.getDescripcion());
			ret.setId(oa.getId() + "");
			//ret.setIdNivel(oa.getIdNivel() + "");
			//ret.setIdRed(oa.getIdRed() + "");
			ret.setNombre(oa.getNombre());
		}
		return ret;
	}
	
	public RedResponse getRedes() {
		RedResponse ret = null;
		List<LibroRedDTO> reds = null;
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
		
		if (reds != null) {
			ret = new RedResponse();
			ret.setRedes(reds);
		}
		
		return ret;
	}
}