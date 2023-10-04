package com.bsodsoftware.merbackend.rest;

import java.util.List;
import java.util.Map;

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

import com.bsodsoftware.merbackend.services.ActividadMerService;
import com.bsodsoftware.merbackend.services.SecurityService;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.ActividadMerResponse;
import com.bsodsoftware.merbackend.services.to.LibroRedDTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/actividadmer")
public class ActividadMerRest {

	@Autowired
	private ActividadMerService actividadMerService;
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarActividad(@RequestBody ActividadMerDTO actividadMerDto, @RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				actividadMerService.guardarActividad(actividadMerDto, idUsuario);
				ret.setCodigo(200);
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getAllActividades")
	@CrossOrigin
	@ResponseBody
	public ActividadMerResponse getActividades(@RequestHeader("Authorization") String token) {
		ActividadMerResponse ret = new ActividadMerResponse();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				List<ActividadMerListaDto> dtos = actividadMerService.getActividades();
				if (dtos != null && !dtos.isEmpty()) {
					ret.setCodigo(200);
					ret.setComentario("Actividades encontradas");
					ret.setActividades(dtos);
				} else {
					ret.setCodigo(500);
					ret.setComentario("Actividades no encontradas");
				}
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@GetMapping("deleteActividad")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteActividad(@RequestParam Long id, @RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				actividadMerService.delete(id);
				ret.setCodigo(200);
				ret.setComentario("Eliminado correctamente");
			} else {
				ret.setCodigo(500);
				ret.setComentario("Usuario de token no encontrado");
			}
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getLibros")
	@CrossOrigin
	@ResponseBody
	public List<LibroRedDTO> getLibrosYRedes(@RequestHeader("Authorization") String token) {
		List<LibroRedDTO> ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = actividadMerService.getLibros();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return ret;
	}
	
	@GetMapping("getActividad")
	@CrossOrigin
	@ResponseBody
	public ActividadMerDTO getActividad(@RequestParam Long id, @RequestHeader("Authorization") String token) {
		ActividadMerDTO ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = actividadMerService.getActividad(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
