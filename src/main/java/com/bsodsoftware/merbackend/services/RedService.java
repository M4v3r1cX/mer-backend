package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.repository.RedRepository;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Service
public class RedService {

	@Autowired
	private RedRepository redRepository;
	
	public void guardarRed(EntidadGenericaDTO redDto) {
		Red red = new Red();
		red.setDescripcion(redDto.getDescripcion());
		red.setNombre(redDto.getNombre());
		redRepository.save(red);
	}
}
