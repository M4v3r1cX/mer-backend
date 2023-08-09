package com.bsodsoftware.merbackend.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.RedDao;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Stateless
public class RedService {

	@Inject
	private RedDao redDao;
	
	public void guardarRed(EntidadGenericaDTO redDto) {
		Red red = new Red();
		red.setDescripcion(redDto.getDescripcion());
		red.setNombre(redDto.getNombre());
		redDao.save(red);
	}
}
