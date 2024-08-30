package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;
import com.bsodsoftware.merbackend.jpa.entities.Nivel;
import com.bsodsoftware.merbackend.jpa.entities.Ruta;
import com.bsodsoftware.merbackend.jpa.entities.SubcategoriaRed;
import com.bsodsoftware.merbackend.jpa.entities.Usuario;
import com.bsodsoftware.merbackend.jpa.repository.RutaRepository;
import com.bsodsoftware.merbackend.services.to.ActividadMerDTO;
import com.bsodsoftware.merbackend.services.to.CargarRutaDbDTO;
import com.bsodsoftware.merbackend.services.to.CrearRutaDTO;
import com.bsodsoftware.merbackend.services.to.RutaDTO;

@Service
public class RutaService {

	@Autowired
	private RutaRepository rutaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@SuppressWarnings("deprecation")
	public Long save(CrearRutaDTO rutaDto, Long idUsuario) {
		Optional<Usuario> usuarioopt = usuarioService.findById(idUsuario);
		Usuario usuario = usuarioopt.get();
		Ruta r = null;
		if (rutaDto.getId() == null || rutaDto.getId().equals(-1L)) {
			r = new Ruta();
			r.setUsuario(usuario);
			r.setNombre(rutaDto.getNombre());
		} else {
			r = rutaRepository.getById(rutaDto.getId());
		}
		
		r.setJsonRuta(rutaDto.getJsonRuta());
		
		r = rutaRepository.save(r);
		
		return r.getId();
	}
	
	public void delete(Long idRuta) {
		rutaRepository.deleteById(idRuta);
	}
	
	public List<CargarRutaDbDTO> getRutasDeUsuario(Long idUsuario) {
		List<CargarRutaDbDTO> ret = null;
		Optional<Usuario> usuarioopt = usuarioService.findById(idUsuario);
		Usuario usuario = usuarioopt.get();
		if (usuario.getRutas() != null && !usuario.getRutas().isEmpty()) {
			ret = new ArrayList<CargarRutaDbDTO>();
			for (Ruta red : usuario.getRutas()) {
				CargarRutaDbDTO dto = new CargarRutaDbDTO();
				dto.setId(red.getId());
				dto.setNombre(red.getNombre());
				dto.setActividades(red.getJsonRuta());
				ret.add(dto);
			}
		}
		return ret;
	}
}
