package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	//https://www.digitalocean.com/community/tutorials/spring-data-jpa
}
