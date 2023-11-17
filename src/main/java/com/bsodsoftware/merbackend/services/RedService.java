package com.bsodsoftware.merbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Red;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.jpa.repository.RedRepository;
import com.bsodsoftware.merbackend.jpa.repository.SubcategoriaRedRepository;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;

@Service
public class RedService {

	@Autowired
	private RedRepository redRepository;
	
	@Autowired
	private SubcategoriaRedRepository subcategoriaRepository;
	
	public void guardarRed(EntidadGenericaDTO redDto) {
		Red red = new Red();
		red.setDescripcion(redDto.getDescripcion());
		red.setNombre(redDto.getNombre());
		save(red);
	}
	
	@SuppressWarnings("deprecation")
	public Red findById(Long id) {
		return redRepository.getOne(id);
	}
	
	public List<Red> findAll() {
		return redRepository.findAll();
	}
	
	public void saveAll(List<Red> redes) {
		redRepository.saveAllAndFlush(redes);
	}
	
	public Red save(Red red) {
		return redRepository.saveAndFlush(red);
	}
	
	public long count() {
		return redRepository.count();
	}
	
	public long countSubcategorias() {
		return subcategoriaRepository.count();
	}
	
	public SubcategoriaRed saveSubcategoria(SubcategoriaRed subcategoria) {
		return subcategoriaRepository.saveAndFlush(subcategoria);
	}
	
	public SubcategoriaRed findSubcategoriaById(Long id) {
		Optional<SubcategoriaRed> op = subcategoriaRepository.findById(id);
		return op.get();
	}
	
	public Red findRedByNombre(String nombre) {
		return redRepository.findOneByNombre(nombre);
	}
}
