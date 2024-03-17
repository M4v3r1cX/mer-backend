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

import com.bsodsoftware.merbackend.services.ObjetivoAprendizajeService;
import com.bsodsoftware.merbackend.services.SecurityService;
import com.bsodsoftware.merbackend.services.TareaMatematicaService;
import com.bsodsoftware.merbackend.services.to.OaTmDto;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;
import com.bsodsoftware.merbackend.services.to.TMDTO;

@RestController
@RequestMapping("/tm")
public class TMRest {

	@Autowired
	private TareaMatematicaService tmService;
	
	@Autowired
	private ObjetivoAprendizajeService oaService;
	
	@Autowired
	private SecurityService securityService;
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarTm(@RequestBody TMDTO tmDto,@RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				tmService.guardarTm(tmDto, idUsuario);
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
	
	@GetMapping("getAllTms")
	@CrossOrigin
	@ResponseBody
	public List<TMDTO> getTms(@RequestHeader("Authorization") String token) {
		List<TMDTO> ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = tmService.getTms();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("deleteTm")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteTm(@RequestParam Long id,@RequestHeader("Authorization") String token) {
		ResponseDTO ret = new ResponseDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				tmService.delete(id);
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
	
	@GetMapping("getAllOaTms")
	@CrossOrigin
	@ResponseBody
	public List<OaTmDto> getOaTms(@RequestHeader("Authorization") String token) {
		List<OaTmDto> ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = oaService.getOasTms();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("getTMsActividadesFiltrar")
	@CrossOrigin
	@ResponseBody
	public List<TMDTO> getTMsActividadesFiltrar(@RequestHeader("Authorization") String token, @RequestParam String nivel, @RequestParam String red) {
		List<TMDTO> ret = null;
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = tmService.getTmsFiltradas(Long.valueOf(nivel), Long.valueOf(red));
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("getTm")
	@CrossOrigin
	@ResponseBody
	public TMDTO getTm(Long id,@RequestHeader("Authorization") String token) {
		TMDTO ret = new TMDTO();
		try {
			Long idUsuario = securityService.validateToken(token);
			if (!idUsuario.equals(-1L)) {
				ret = tmService.getTmDto(id);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
