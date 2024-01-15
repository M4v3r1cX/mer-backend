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
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;
import com.bsodsoftware.merbackend.jpa.repository.TareaMatematicaRepository;
import com.bsodsoftware.merbackend.services.to.TMDTO;

@Service
public class TareaMatematicaService {

	@Autowired
	private TareaMatematicaRepository tmRepository;
	
	@Autowired
	private ObjetivoAprendizajeService oaService;
	
	@Autowired
	private AuditoriaService auditoriaService;
	
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
		tm = save(tm);
		
		ObjetivoAprendizajeHijo oaHijo = oaService.findOaHijoById(Long.valueOf(tmDto.getIdOa()));
		if (oaHijo != null) {
			oaHijo.addTareaMatematica(tm);
			oaService.save(oaHijo);
			tm.setObjetivoAprendizajeHijo(oaHijo);
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
				ObjetivoAprendizajeHijo oaHijo = tm.getObjetivoAprendizajeHijo();
				if (oaHijo != null) {
					tmdto.setIdOa(oaHijo.getId() + "");
					tmdto.setDescripcionOa(oaHijo.getDescripcion());
					ObjetivoAprendizaje oaPadre = oaHijo.getObjetivoAprendizaje();
					tmdto.setCodigoOa(oaPadre.getNombre());
					for (SubcategoriaRed s : oaHijo.getSubcategorias()) {
						tmdto.addRed(s.getRed().getNombre());
					}
					for (Nivel n : oaPadre.getNiveles()) {
						tmdto.addNivel(n.getNombre());
					}
				}
				ret.add(tmdto);
			}
		}
		return ret;
	}
	
	public List<TMDTO> getTmsFiltradas(Long idNivel, Long idRed) {
		List<TMDTO> ret = null;
		List<TareaMatematica> tms = tmRepository.findAllFiltrados(idNivel, idRed);
		if (tms != null && !tms.isEmpty()) {
			ret = new ArrayList<TMDTO>();
			for (TareaMatematica tm : tms) {
				TMDTO tmdto = new TMDTO();
				tmdto.setId(tm.getId() + "");
				tmdto.setDescripcion(tm.getDescripcion());
				ObjetivoAprendizajeHijo oaHijo = tm.getObjetivoAprendizajeHijo();
				if (oaHijo != null) {
					tmdto.setIdOa(oaHijo.getId() + "");
					tmdto.setDescripcionOa(oaHijo.getDescripcion());
					ObjetivoAprendizaje oaPadre = oaHijo.getObjetivoAprendizaje();
					tmdto.setCodigoOa(oaPadre.getNombre());
					for (SubcategoriaRed s : oaHijo.getSubcategorias()) {
						tmdto.addRed(s.getRed().getNombre());
					}
					for (Nivel n : oaPadre.getNiveles()) {
						tmdto.addNivel(n.getNombre());
					}
				}
				ret.add(tmdto);
			}
		}
		return ret;
	}
	
	public TMDTO getTmDto(Long idTM) {
		TMDTO ret = null;
		Optional<TareaMatematica> ot = tmRepository.findById(idTM);
		if (ot.get() != null) {
			ret = new TMDTO();
			ret.setId(ot.get().getId() + "");
			ret.setDescripcion(ot.get().getDescripcion());
			ret.setIdOa(ot.get().getObjetivoAprendizajeHijo().getId() + "");
		}
		return ret;
	}
	
	public TareaMatematica getTm(Long idTm) {
		TareaMatematica ret = null;
		Optional<TareaMatematica> otm = tmRepository.findById(idTm);
		if (otm.get() != null) {
			ret = otm.get();
		}
		return ret;
	}
}
