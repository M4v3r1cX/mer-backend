package com.bsodsoftware.merbackend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bsodsoftware.merbackend.services.MapasService;
import com.bsodsoftware.merbackend.services.to.MapaOADTO;

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
}
