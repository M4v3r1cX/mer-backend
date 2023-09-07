package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;
import com.bsodsoftware.merbackend.jpa.repository.TareaMatematicaRepository;
import com.bsodsoftware.merbackend.services.to.TMDTO;

@Service
public class TareaMatematicaService {

	@Autowired
	private TareaMatematicaRepository tmRepository;
	
	@Autowired
	private ObjetivoAprendizajeService oaService;
	
	private TareaMatematica save(TareaMatematica tm) {
		try {
			return tmRepository.saveAndFlush(tm);
		} catch (Exception ex) {
			ex.printStackTrace();
			return tm;
		}
	}
	
	public void guardarTm(TMDTO tmDto, Long idUsuario) {
		TareaMatematica tm = null;
		if (tmDto.getId() != null && !tmDto.getId().isEmpty()) {
			tm = tmRepository.getReferenceById(Long.valueOf(tmDto.getId()));
		} else {
			tm = new TareaMatematica();
		}
		tm.setDescripcion(tmDto.getDescripcion());
		tm.setIdUsuario(idUsuario);
		save(tm);
		ObjetivoAprendizaje oa = oaService.findOaById(Long.valueOf(tmDto.getIdOa()));
		if (oa != null) {
			oa.addTm(tm);
			oaService.save(oa);
			tm.setObjetivoAcademico(oa);
			save(tm);
		}
	}
	
	public void delete(Long id) {
		tmRepository.deleteById(id);
	}
	
	public List<TMDTO> getTms() {
		List<TMDTO> ret = null;
		List<TareaMatematica> tms = tmRepository.findAll();
		if (tms != null && !tms.isEmpty()) {
			ret = new ArrayList<TMDTO>();
			for (TareaMatematica tm : tms) {
				TMDTO tmdto = new TMDTO();
				tmdto.setId(tm.getId() + "");
				tmdto.setDescripcion(tm.getDescripcion());
				ObjetivoAprendizaje oa = tm.getObjetivoAcademico();
				tmdto.setIdOa(oa.getId() + "");
				tmdto.setCodigoOa(oa.getNombre());
				ret.add(tmdto);
			}
		}
		return ret;
	}
	
	public TMDTO getTm(Long idTM) {
		TMDTO ret = null;
		Optional<TareaMatematica> ot = tmRepository.findById(idTM);
		if (ot.get() != null) {
			ret = new TMDTO();
			ret.setId(ot.get().getId() + "");
			ret.setDescripcion(ot.get().getDescripcion());
			ret.setIdOa(ot.get().getObjetivoAcademico().getId() + "");
		}
		return ret;
	}
}
