package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAcademico;
import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAcademicoRepository;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.LibroRedResponse;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.RedResponse;

@Service
public class ObjetivoAcademicoService {
	
	@Autowired
	private RedService redService;

	@Autowired
	private ObjetivoAcademicoRepository oaRepository;
	
	private void save(ObjetivoAcademico entity) {
		oaRepository.saveAndFlush(entity);
	}
	
	public void guardarOa(OaDTO oadto, Long idUsuario) {
		ObjetivoAcademico oa = null;
		if (oadto.getId() != null && !oadto.getId().isEmpty()) {
			oa = oaRepository.getReferenceById(Long.valueOf(oadto.getId()));
		} else {
			oa = new ObjetivoAcademico();
		}
		oa.setDescripcion(oadto.getDescripcion());
		oa.setIdNivel(Long.valueOf(oadto.getIdNivel()));
		oa.setIdRed(Long.valueOf(oadto.getIdRed()));
		oa.setIdUsuario(idUsuario);
		oa.setNombre(oadto.getNombre());
		save(oa);
	}
	
	public void delete(Long id) {
		oaRepository.deleteById(id);
	}
	
	public List<OaDTO> getOas() {
		List<OaDTO> ret = null;
		List<ObjetivoAcademico> oas = oaRepository.findAll();
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<OaDTO>();
			for (ObjetivoAcademico oa : oas) {
				OaDTO oadto = new OaDTO();
				oadto.setId(oa.getId() + "");
				oadto.setIdNivel(oa.getIdNivel() + "");
				oadto.setIdRed(oa.getIdRed() + "");
				oadto.setNombre(oa.getNombre());
				oadto.setDescripcion(oa.getDescripcion());
				ret.add(oadto);
			}
		}
		
		return ret;
	}
	
	public OaDTO getOa(Long id) {
		OaDTO ret = null;
		ObjetivoAcademico oa = oaRepository.getReferenceById(id);
		if (oa != null) {
			ret = new OaDTO();
			ret.setDescripcion(oa.getDescripcion());
			ret.setId(oa.getId() + "");
			ret.setIdNivel(oa.getIdNivel() + "");
			ret.setIdRed(oa.getIdRed() + "");
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
