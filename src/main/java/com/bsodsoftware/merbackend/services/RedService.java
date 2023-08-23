package com.bsodsoftware.merbackend.services;

import java.util.List;

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
	
	public long count() {
		return redRepository.count();
	}
}
