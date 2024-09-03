package com.bsodsoftware.merbackend.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.RutaService;
import com.bsodsoftware.merbackend.services.SecurityService;
import com.bsodsoftware.merbackend.services.to.CargarRutaDbDTO;
import com.bsodsoftware.merbackend.services.to.CrearRutaDTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/ruta")
public class RutaRest {
	
	@Autowired
	private RutaService rutaService;
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/saveRuta")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarActividad(@RequestBody CrearRutaDTO crearRutaDto, @RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				Long idRuta = rutaService.save(crearRutaDto, idUsuario);
				ret.setCodigo(200);
				ret.setComentario(idRuta + "");
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}

	@GetMapping("/getRutasUsuario")
	@CrossOrigin
	@ResponseBody
	public List<CargarRutaDbDTO> getRutasUsuario(@RequestHeader("Authorization") String token) {
		List<CargarRutaDbDTO> ret = new ArrayList<CargarRutaDbDTO>();
		
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = rutaService.getRutasDeUsuario(idUsuario);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("/deleteRutaUsuario")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteRutaUsuario(@RequestHeader("Authorization") String token, @RequestParam("id") Long id) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				rutaService.delete(id);
				ret.setCodigo(200);
				ret.setComentario("Ruta eliminada correctamente.");
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
			ex.printStackTrace();
		}
		
		return ret;
	}
}
