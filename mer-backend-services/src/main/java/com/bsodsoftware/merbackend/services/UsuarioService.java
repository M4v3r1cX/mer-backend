package com.bsodsoftware.merbackend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.UsuarioRepository;
import com.bsodsoftware.merbackend.services.to.UsuarioDTO;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Usuario save(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public void createUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setFechaCreacion(new Date());
		usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
		usuario.setInstitucion(null);
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setNombreUsuario(usuarioDto.getUsuario());
		
		save(usuario);
	}
	
	public boolean login(String username, String passwd) throws Exception {
		boolean ret = false;
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario != null) {
			if (passwordEncoder.matches(passwd, usuario.getPassword())) {
				ret = true;
			} else {
				throw new Exception ("Contraseña incorrecta.");
			}
		} else {
			throw new Exception ("Usuario con correo " + username + " no encontrado.");
		}
		return ret;
	}
	
	//https://www.digitalocean.com/community/tutorials/spring-data-jpa
}
