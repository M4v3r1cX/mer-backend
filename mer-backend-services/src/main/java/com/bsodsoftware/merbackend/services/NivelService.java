package com.bsodsoftware.merbackend.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.repository.NivelDao;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Stateless
public class NivelService {

	@Inject
	private NivelDao nivelDao;
	
	public void guardarNivel(EntidadGenericaDTO nivelDto) {
		Nivel nivel = new Nivel();
		nivel.setNombre(nivelDto.getNombre());
		nivelDao.save(nivel);
	}
}
