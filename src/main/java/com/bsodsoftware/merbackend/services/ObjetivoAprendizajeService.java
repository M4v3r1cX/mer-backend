package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAprendizajeHijoRepository;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAprendizajeRepository;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.OaTmDto;
import com.bsodsoftware.merbackend.services.to.RedResponse;

@Service
public class ObjetivoAprendizajeService {
	
	@Autowired
	private RedService redService;
	
	@Autowired
	private NivelService nivelService;

	@Autowired
	private ObjetivoAprendizajeRepository oaRepository;
	
	@Autowired
	private ObjetivoAprendizajeHijoRepository oaHijoRepository;
	
	public void save(ObjetivoAprendizaje entity) {
		oaRepository.saveAndFlush(entity);
	}
	
	public void save(ObjetivoAprendizajeHijo entity) {
		oaHijoRepository.saveAndFlush(entity);
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
		oa.setNombre(oadto.getCodigo());
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
				oahijo.setPriorizado(oahijodto.getPrioridad());
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
		oaHijoRepository.saveAll(oa.getHijos());
	}
	
	public void delete(Long id) {
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null) {
			if (oa.getHijos() != null && !oa.getHijos().isEmpty()) {
				for (ObjetivoAprendizajeHijo oahijo : oa.getHijos()) {
					oaHijoRepository.delete(oahijo);
				}
			}
		}
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
				oadto.setCodigo(oa.getNombre());
				oadto.setDescripcion(oa.getDescripcion());
				oadto.setPrioridad(oa.isPriorizado());
				ret.add(oadto);
			}
		}
		
		return ret;
	}
	
	public List<OaTmDto> getOasTms() {
		List<OaTmDto> ret = null;
		List<ObjetivoAprendizajeHijo> oas = oaHijoRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaTmDto>();
			for (ObjetivoAprendizajeHijo oa : oas) {
				OaTmDto otd = new OaTmDto();
				otd.setId(oa.getId() + "");
				otd.setCodigo(oa.getObjetivoAprendizaje().getNombre());
				otd.setDescripcion(oa.getDescripcion());
				ret.add(otd);
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
			ret.setCodigo(oa.getNombre());
		}
		return ret;
	}
	
	public ObjetivoAprendizaje findOaById(Long id) {
		ObjetivoAprendizaje oa = null;
		try {
			Optional<ObjetivoAprendizaje> op = oaRepository.findById(id);
			oa = op.get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oa;
	}
	
	public ObjetivoAprendizajeHijo findOaHijoById(Long id) {
		ObjetivoAprendizajeHijo oaHijo = null;
		try {
			Optional<ObjetivoAprendizajeHijo> op = oaHijoRepository.findById(id);
			oaHijo = op.get();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return oaHijo;
	}
	
	public List<OaHijoDTO> getHijos(Long id) {
		List<OaHijoDTO> ret = null;
		
		ObjetivoAprendizaje oa = oaRepository.getReferenceById(id);
		if (oa != null && oa.getHijos() != null && !oa.getHijos().isEmpty()) {
			ret = new ArrayList<OaHijoDTO>();
			for (ObjetivoAprendizajeHijo oahijo : oa.getHijos()) {
				OaHijoDTO oadto = new OaHijoDTO();
				oadto.setDescripcion(oahijo.getDescripcion());
				oadto.setPrioridad(oahijo.isPriorizado() != null ? oahijo.isPriorizado() : false);
				for (Red r : oahijo.getRedes()) {
					oadto.addRed(r.getNombre());
				}
				for (Nivel n : oahijo.getNiveles()) {
					oadto.addNivel(n.getNombre());
				}
				ret.add(oadto);
			}
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
