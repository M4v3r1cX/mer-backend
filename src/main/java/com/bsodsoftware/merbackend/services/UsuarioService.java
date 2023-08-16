package com.bsodsoftware.merbackend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.UsuarioRepository;
import com.bsodsoftware.merbackend.services.to.RegisterDTO;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario save(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public boolean createUsuario(RegisterDTO usuarioDto) throws Exception {
		boolean ret = false;
		if (usuarioDto.getPassword().equals(usuarioDto.getPassword2())) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			Usuario usuario = new Usuario();
			usuario.setEmail(usuarioDto.getEmail());
			usuario.setFechaCreacion(new Date());
			usuario.setPassword(bcrypt.encode(usuarioDto.getPassword()));
			usuario.setNombre(usuarioDto.getNombre());
			
			save(usuario);
			ret = true;
		} else {
			throw new Exception("Contraseñas no coinciden.");
		}
		return ret;
	}
	
	public boolean login(String username, String passwd) throws Exception {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		boolean ret = false;
		Usuario usuario = usuarioRepository.findByEmail(username);
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