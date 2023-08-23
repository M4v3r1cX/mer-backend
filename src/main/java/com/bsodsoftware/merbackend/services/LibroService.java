package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.repository.LibroRepository;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaResponseDTO;

@Service
public class LibroService {

	@Autowired
	LibroRepository libroRepository;
	
	public Libro save(Libro libro) {
		return libroRepository.save(libro);
	}
	
	public void save(List<Libro> libros) {
		libroRepository.saveAllAndFlush(libros);
	}
	
	public void guardarLibro(EntidadGenericaDTO libroDto) {
		Libro libro = new Libro();
		libro.setDescripcion(libroDto.getDescripcion());
		libro.setNombre(libroDto.getNombre());
		save(libro);
	}
	
	public List<EntidadGenericaResponseDTO> findLibros() {
		List<Libro> libros = libroRepository.findAll();
		List<EntidadGenericaResponseDTO> ret = null;
		
		if (libros != null && !libros.isEmpty()) {
			ret = new ArrayList<EntidadGenericaResponseDTO>();
			for(Libro l : libros) {
				EntidadGenericaResponseDTO dto = new EntidadGenericaResponseDTO();
				dto.setId(l.getId() + "");
				dto.setNombre(l.getNombre());
				ret.add(dto);
			}
		}
		
		return ret;
	}
	
	public void deleteLibro(Long id) {
		libroRepository.deleteById(id);
	}
	
	@SuppressWarnings("deprecation")
	public Libro findById(Long id) {
		return libroRepository.getOne(id);
	}
	
	public List<Libro> findAll() {
		return libroRepository.findAll();
	}
	
	public long count() {
		return libroRepository.count();
	}
}
