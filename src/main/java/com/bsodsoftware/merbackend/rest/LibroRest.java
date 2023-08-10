package com.bsodsoftware.merbackend.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.LibroService;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/libro")
public class LibroRest {

	@Autowired
	private LibroService libroService;
	
	@PostMapping("/save")
	@ResponseBody
	public ResponseDTO guardarLibro(@RequestBody EntidadGenericaDTO libroDto) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			libroService.guardarLibro(libroDto);
			ret.setCodigo(200);
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		
		return ret;
	}
}
