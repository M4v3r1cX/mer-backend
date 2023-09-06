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

import com.bsodsoftware.merbackend.services.ObjetivoAprendizajeService;
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
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarTm(@RequestBody TMDTO tmDto) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			tmService.guardarTm(tmDto, 1L);
			ret.setCodigo(200);
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getAllTms")
	@CrossOrigin
	@ResponseBody
	public List<TMDTO> getTms() {
		return tmService.getTms();
	}
	
	@GetMapping("deleteTm")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteTm(@RequestParam Long id) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			tmService.delete(id);
			ret.setCodigo(200);
			ret.setComentario("Eliminado correctamente");
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getAllOaTms")
	@CrossOrigin
	@ResponseBody
	public List<OaTmDto> getOaTms() {
		return oaService.getOasTms();
	}
}
