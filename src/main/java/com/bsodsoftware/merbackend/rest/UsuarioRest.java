package com.bsodsoftware.merbackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.UsuarioService;
import com.bsodsoftware.merbackend.services.to.LoginDTO;
import com.bsodsoftware.merbackend.services.to.RegisterDTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/login")
	@ResponseBody
	public ResponseDTO login(@RequestBody LoginDTO loginDto) {
		ResponseDTO ret = new ResponseDTO();
		try {
			if(usuarioService.login(loginDto.getUsuario(), loginDto.getPassword())) {
				ret.setCodigo(200);
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@PostMapping("/register")
	@ResponseBody
	public ResponseDTO register(@RequestBody RegisterDTO registerDto) {
		ResponseDTO ret = new ResponseDTO();
		try {
			if(usuarioService.createUsuario(registerDto)) {
				ret.setCodigo(200);
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
}
