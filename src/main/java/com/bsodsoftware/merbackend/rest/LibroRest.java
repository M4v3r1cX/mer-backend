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

import com.bsodsoftware.merbackend.services.LibroService;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaDTO;
import com.bsodsoftware.merbackend.services.to.EntidadGenericaResponseDTO;
import com.bsodsoftware.merbackend.services.to.LibroResponse;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@RestController
@RequestMapping("/libro")
public class LibroRest {

	@Autowired
	private LibroService libroService;
	
	@PostMapping("/save")
	@CrossOrigin
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
	
	@GetMapping("getAllLibros")
	@CrossOrigin
	@ResponseBody
	public LibroResponse getLibros() {
		LibroResponse ret = new LibroResponse();
		List<EntidadGenericaResponseDTO> dtos = libroService.findLibros();
		if (dtos != null && !dtos.isEmpty()) {
			ret.setCodigo(200);
			ret.setComentario("Libros encontrados");
			ret.setEntidades(dtos);
		} else {
			ret.setCodigo(500);
			ret.setComentario("Libros no encontrados");
		}
		return ret;
	}
	
	@GetMapping("deleteLibro")
	@CrossOrigin
	@ResponseBody
	public ResponseDTO deleteLibro(@RequestParam Long id) {
		ResponseDTO ret = new ResponseDTO();
		
		try {
			libroService.deleteLibro(id);
			ret.setCodigo(200);
			ret.setComentario("Eliminado correctamente");
		} catch (Exception ex) {
			ret.setCodigo(500);
			ret.setComentario(ex.getLocalizedMessage());
		}
		
		return ret;
	}
}
