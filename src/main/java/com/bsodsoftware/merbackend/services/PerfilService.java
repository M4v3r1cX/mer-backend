package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Perfil;
import com.bsodsoftware.merbackend.jpa.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@SuppressWarnings("deprecation")
	private Perfil getPerfil(Long id) {
		return perfilRepository.getById(id);
	}
	
	public Perfil getPerfilAdministrador() {
		return getPerfil(1L);
	}
	
	public Perfil getPerfilRedes() {
		return getPerfil(2L);
	}
}
