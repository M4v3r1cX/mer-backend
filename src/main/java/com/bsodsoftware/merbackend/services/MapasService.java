package com.bsodsoftware.merbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.services.to.MapaOADTO;

@Service
public class MapasService {
	
	@Autowired
	ObjetivoAprendizajeService objetivoAprendizajeService;

	public List<MapaOADTO> getOasByRed(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
