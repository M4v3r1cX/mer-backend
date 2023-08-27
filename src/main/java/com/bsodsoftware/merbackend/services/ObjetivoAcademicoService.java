package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAcademico;
import com.bsodsoftware.merbackend.jpa.repository.ObjetivoAcademicoRepository;
import com.bsodsoftware.merbackend.services.to.OaDTO;

@Service
public class ObjetivoAcademicoService {

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
}
