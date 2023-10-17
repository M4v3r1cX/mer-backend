package com.bsodsoftware.merbackend.services;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Auditoria;
import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.UsuarioRepository;
import com.bsodsoftware.merbackend.services.to.RegisterDTO;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class UsuarioService {
	
	private String secret = "zE0/_2C])D2kS0072B_a}Ces[0T.r.,X'},tKJemUSeG/&7nhu"; // holy fuck 
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	AuditoriaService auditoriaService;
	
	public Usuario save(Usuario entity) {
		return usuarioRepository.save(entity);
	}
	
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Optional<Usuario> findById(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public String getSecret() {
		return secret;
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
	
	public String login(String username, String passwd) throws Exception {
		System.out.println("Intentando realizar login para el usuario " + username);
		String token = null;
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario != null) {
			if (bcrypt.matches(passwd, usuario.getPassword())) {
				System.out.println("Usuario loggeado correctamente.");
				//SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
				token = Jwts.builder()
						.claim("name", usuario.getNombre())
						.claim("mail", username)
						.claim("id", usuario.getId())
						.setSubject(usuario.getNombre())
						.setId(UUID.randomUUID().toString())
						.setIssuedAt(Date.from(Instant.now()))
						.setExpiration(Date.from(Instant.now().plus(51, ChronoUnit.MINUTES)))
						.signWith(getSigningKey(), SignatureAlgorithm.HS256)
						.compact();
				auditoriaService.guardarAccion(Auditoria.ACCION.LOGIN, usuario.getId(), null);
			} else {
				System.out.println("Contraseña incorrecta.");
				throw new Exception ("Contraseña incorrecta.");
			}
		} else {
			throw new Exception ("Usuario con correo " + username + " no encontrado.");
		}
		return token;
	}
	
	private Key getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
