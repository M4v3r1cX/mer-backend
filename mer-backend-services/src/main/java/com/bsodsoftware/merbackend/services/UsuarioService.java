package com.bsodsoftware.merbackend.services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.UsuarioDao;
import com.bsodsoftware.merbackend.services.to.UsuarioDTO;

@Stateless
public class UsuarioService {

	@Inject
	UsuarioDao usuarioDao;
	
	public void save(Usuario entity) {
		usuarioDao.save(entity);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioDao.findByEmail(email);
	}
	
	public void createUsuario(UsuarioDTO usuarioDto) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Usuario usuario = new Usuario();
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setFechaCreacion(new Date());
		usuario.setPassword(bcrypt.encode(usuarioDto.getPassword()));
		usuario.setInstitucion(null);
		usuario.setNombre(usuarioDto.getNombre());
		usuario.setNombreUsuario(usuarioDto.getUsuario());
		
		save(usuario);
	}
	
	public boolean login(String username, String passwd) throws Exception {
		boolean ret = false;
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Usuario usuario = usuarioDao.findByEmail(username);
		if (usuario != null) {
			if (bcrypt.matches(passwd, usuario.getPassword())) {
				ret = true;
			} else {
				throw new Exception ("Contraseña incorrecta.");
			}
		} else {
			throw new Exception ("Usuario con correo " + username + " no encontrado.");
		}
		return ret;
	}
}
