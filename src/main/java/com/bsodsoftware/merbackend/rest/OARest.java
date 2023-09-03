package com.bsodsoftware.merbackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.ActividadMerService;
import com.bsodsoftware.merbackend.services.ObjetivoAprendizajeService;
import com.bsodsoftware.merbackend.services.to.LibroRedResponse;
import com.bsodsoftware.merbackend.services.to.OAResponse;
import com.bsodsoftware.merbackend.services.to.OaDTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.RedResponse;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/oa")
public class OARest {
	
	@Autowired
	private ObjetivoAprendizajeService oaService;
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarOa(@RequestBody OaDTO oaDto) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			oaService.guardarOa(oaDto, 1L);
			ret.setCodigo(200);
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getAllOas")
	@CrossOrigin
	@ResponseBody
	public OAResponse getOas() {
		OAResponse ret = new OAResponse();
		List<OaDTO> oas = oaService.getOas();
		if (oas != null && !oas.isEmpty()) {
			ret.setCodigo(200);
			ret.setComentario("OAs encontrados");
			ret.setOas(oas);
		} else {
			ret.setCodigo(500);
			ret.setComentario("OAs no encontrados");
		}
		return ret;
	}
	
	@GetMapping("deleteOa")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteOa(@RequestParam Long id) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			oaService.delete(id);
			ret.setCodigo(200);
			ret.setComentario("Eliminado correctamente");
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getRedes")
	@CrossOrigin
	@ResponseBody
	public RedResponse getRedes() {
		RedResponse ret = oaService.getRedes();
		
		return ret;
	}
	
	@GetMapping("getOa")
	@CrossOrigin
	@ResponseBody
	public OaDTO getOa(@RequestParam Long id) {
		OaDTO ret = oaService.getOa(id);
		return ret;
	}
	
	@GetMapping("getHijos")
	@CrossOrigin
	@ResponseBody
	public List<OaHijoDTO> getHijos(@RequestParam Long id) {
		List<OaHijoDTO> ret = oaService.getHijos(id);
		return ret;
	}
}
