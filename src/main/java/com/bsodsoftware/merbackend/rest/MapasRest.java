package com.bsodsoftware.merbackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;
import com.bsodsoftware.merbackend.services.MapasService;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.MapaOADTO;
import com.bsodsoftware.merbackend.services.to.OaHijoDTO;
import com.bsodsoftware.merbackend.services.to.TMDTO;

@RestController
@RequestMapping("/mapas")
public class MapasRest {

	@Autowired
	private MapasService mapasService;
	
	@GetMapping("/getOasByRed")
	@CrossOrigin
	@ResponseBody
	public List<MapaOADTO> getOAS(@RequestParam Long id) {
		return mapasService.getOasByRed(id);
	}
	
	@GetMapping("/getOasHijosByRed")
	@CrossOrigin
	@ResponseBody
	public List<MapaOADTO> getOASHijos(@RequestParam Long id) {
		return mapasService.getOasHijosByRed(id);
	}
	
	@GetMapping("/getOasHijosByOa")
	@CrossOrigin
	@ResponseBody
	public List<OaHijoDTO> getHijos(@RequestParam Long id) {
		List<OaHijoDTO> ret = null;
		try {
			ret = mapasService.getHijos(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/getTareasMatematicasByOaHijo")
	@CrossOrigin
	@ResponseBody
	public List<TMDTO> getTareasMatematicasByOaHijo(@RequestParam Long id) {
		List<TMDTO> ret = null;
		try {
			ret = mapasService.getTareasMatematicasByIdOaHijo(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
	
	@GetMapping("/getActividadesByTareaMatematica")
	@CrossOrigin
	@ResponseBody
	public List<ActividadMerDTO> getActividadesByIdTareaMatematica(@RequestParam Long id) {
		List<ActividadMerDTO> ret = null;
		try {
			ret = mapasService.getActividadesByTareaMatematica(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ret;
	}
}
