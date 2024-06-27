package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;
import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;
import com.bsodsoftware.merbackend.services.to.MapaOADTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.TMDTO;

@Service
public class MapasService {
	
	@Autowired
	ObjetivoAprendizajeService objetivoAprendizajeService;
	
	@Autowired
	TareaMatematicaService tareaMatematicaService;

	public List<MapaOADTO> getOasByRed(Long id) {
		List<MapaOADTO> ret = null;
		List<ObjetivoAprendizaje> oas = objetivoAprendizajeService.findObjetivosByRed(id);
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<MapaOADTO>();
			for (ObjetivoAprendizaje oa : oas) {
				MapaOADTO mapa = new MapaOADTO();
				mapa.setCodigo(oa.getNombre());
				mapa.setDescripcion(oa.getDescripcion());
				mapa.setId(oa.getId() + "");
				mapa.setPrioridad(oa.isPriorizado());
				mapa.setIdNivel(oa.getNiveles().get(0).getId());
				mapa.setIdSubcategoria(oa.getHijos().get(0).getSubcategorias().get(0).getId());
				ret.add(mapa);
			}
		}
		return ret;
	}
	
	public List<MapaOADTO> getOasHijosByRed(Long id) {
		List<MapaOADTO> ret = null;
		List<ObjetivoAprendizajeHijo> oas = objetivoAprendizajeService.findObjetivosHijosByRed(id);
		if (oas != null && !oas.isEmpty()) {
			ret = new ArrayList<MapaOADTO>();
			for (ObjetivoAprendizajeHijo oa : oas) {
				MapaOADTO mapa = new MapaOADTO();
				mapa.setCodigo(oa.getObjetivoAprendizaje().getNombre());
				mapa.setDescripcion(oa.getDescripcion());
				mapa.setId(oa.getId() + "");
				mapa.setPrioridad(oa.isPriorizado());
				Long nivel = 0L;
				if (oa.getNiveles() != null && !oa.getNiveles().isEmpty()) {
					nivel = oa.getNiveles().get(0).getId();
				} else {
					nivel = oa.getObjetivoAprendizaje().getNiveles().get(0).getId();
				}
				mapa.setIdNivel(nivel);
				mapa.setIdSubcategoria(oa.getSubcategorias().get(0).getId());
				if (oa.getPosicionOa() != null) {
					mapa.setX(oa.getPosicionOa().getX());
					mapa.setY(oa.getPosicionOa().getY());
					mapa.setTienePosicionamiento(true);
				}
				ret.add(mapa);
			}
		}
		return ret;
	}

	public List<OaHijoDTO> getHijos(Long id) {
		return objetivoAprendizajeService.getHijos(id);
	}
	
	public List<TMDTO> getTareasMatematicasByIdOaHijo(Long idOaHijo) {
		return tareaMatematicaService.getTareasMatematicasByOaHijvo(idOaHijo);
	}

}
