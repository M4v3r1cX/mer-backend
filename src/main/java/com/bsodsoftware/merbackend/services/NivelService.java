package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.repository.NivelRepository;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Service
public class NivelService {

	@Autowired
	private NivelRepository nivelRepository;
	
	public void guardarNivel(EntidadGenericaDTO nivelDto) {
		Nivel nivel = new Nivel();
		nivel.setNombre(nivelDto.getNombre());
		nivelRepository.save(nivel);
	}
}
