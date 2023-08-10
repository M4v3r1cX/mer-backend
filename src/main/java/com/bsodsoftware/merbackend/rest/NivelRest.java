package com.bsodsoftware.merbackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.NivelService;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/nivel")
public class NivelRest {

	@Autowired
	private NivelService nivelService;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseDTO save(@RequestBody EntidadGenericaDTO nivel) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			nivelService.guardarNivel(nivel);
			ret.setCodigo(200);
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		
		return ret;
	}
}
