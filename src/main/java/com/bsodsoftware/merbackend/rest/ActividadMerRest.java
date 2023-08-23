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

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;
import com.bsodsoftware.merbackend.services.ActividadMerService;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.ActividadMerResponse;
import com.bsodsoftware.merbackend.services.to.LibroRedResponse;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/actividadmer")
public class ActividadMerRest {

	@Autowired
	private ActividadMerService actividadMerService;
	
	@PostMapping("/save")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO guardarActividad(@RequestBody ActividadMerDTO actividadMerDto) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			actividadMerService.guardarActividad(actividadMerDto, 1L);
			ret.setCodigo(200);
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getAllActividades")
	@CrossOrigin
	@ResponseBody
	public ActividadMerResponse getActividades() {
		ActividadMerResponse ret = new ActividadMerResponse();
		List<ActividadMerListaDto> dtos = actividadMerService.getActividades();
		if (dtos != null && !dtos.isEmpty()) {
			ret.setCodigo(200);
			ret.setComentario("Actividades encontradas");
			ret.setActividades(dtos);
		} else {
			ret.setCodigo(500);
			ret.setComentario("Actividades no encontradas");
		}
		return ret;
	}
	
	@GetMapping("deleteActividad")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteActividad(@RequestParam Long id) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			actividadMerService.delete(id);
			ret.setCodigo(200);
			ret.setComentario("Eliminado correctamente");
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
		}
		
		return ret;
	}
	
	@GetMapping("getLibrosYRedes")
	@CrossOrigin
	@ResponseBody
	public LibroRedResponse getLibrosYRedes() {
		LibroRedResponse ret = actividadMerService.getLibrosYRedes();
		
		
		return ret;
	}
}
