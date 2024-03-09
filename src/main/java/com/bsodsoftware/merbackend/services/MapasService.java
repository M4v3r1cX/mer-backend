package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.services.to.MapaOADTO;

@Service
public class MapasService {
	
	@Autowired
	ObjetivoAprendizajeService objetivoAprendizajeService;

	public List<MapaOADTO> getOasByRed(Long id) {
		List<MapaOADTO> ret = null;
		List<ObjetivoAprendizaje> oas = objetivoAprendizajeService.findObjetivosByRed(id);
		if (oas != null && oas.isEmpty()) {
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

}
