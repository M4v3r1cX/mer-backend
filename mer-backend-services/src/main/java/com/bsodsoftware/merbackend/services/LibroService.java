package com.bsodsoftware.merbackend.services;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.repository.LibroDao;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Stateless
public class LibroService {

	@Inject
	LibroDao libroDao;
	
	public void save(Libro libro) {
		libroDao.save(libro);
	}
	
	public void guardarLibro(EntidadGenericaDTO libroDto) {
		Libro libro = new Libro();
		libro.setDescripcion(libroDto.getDescripcion());
		libro.setNombre(libroDto.getNombre());
		save(libro);
	}
}
