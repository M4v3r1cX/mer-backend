package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.repository.LibroRepository;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepository;
	
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}
	
	public void guardarLibro(EntidadGenericaDTO libroDto) {
		Libro libro = new Libro();
		libro.setDescripcion(libroDto.getDescripcion());
		libro.setNombre(libroDto.getNombre());
		save(libro);
	}
}