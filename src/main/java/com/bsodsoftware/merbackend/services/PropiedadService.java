package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Propiedad;
import com.bsodsoftware.merbackend.jpa.repository.PropiedadRepository;

@Service
public class PropiedadService {

	@Autowired
	private PropiedadRepository propRepository;
	
	public Propiedad getPropiedad(String prop, String defaultValue) {
		Propiedad ret = null;
		
		Propiedad p = propRepository.findByProp(prop);
		if (p == null) {
			p = new Propiedad();
			p.setProp(prop);
			p.setValue(defaultValue);
			p = propRepository.saveAndFlush(p);
		}
		ret = p;
		
		return ret;
	}
}
