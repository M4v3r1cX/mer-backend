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
import com.bsodsoftware.merbackend.services.to.ActividadMerListaDto;
import com.bsodsoftware.merbackend.services.to.CrearRutaDTO;
import com.bsodsoftware.merbackend.services.to.RutaDTO;

@Service
public class RutaService {

	@Autowired
	private RutaRepository rutaRepository;
	
	@Autowired
	private ActividadMerService actividadMerService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public Ruta save(CrearRutaDTO rutaDto, Long idUsuario) {
		Optional<Usuario> usuarioopt = usuarioService.findById(idUsuario);
		Usuario usuario = usuarioopt.get();
		Ruta r = new Ruta();
		r.setUsuario(usuario);
		r.setNombre(rutaDto.getNombre());
		for (Long id : rutaDto.getIdsActividades()) {
			ActividadMer actividad = actividadMerService.getActividadEntidad(id);
			if (actividad != null) {
				r.addPuntoRuta(actividad);
			}
		}
		if (r.getPuntosRuta() != null && !r.getPuntosRuta().isEmpty()) {
			rutaRepository.save(r);
		}
		return r;
	}
	
	public void delete(Long idRuta) {
		rutaRepository.deleteById(idRuta);
	}
	
	public void updateRuta(CrearRutaDTO rutaDto, Usuario usuario) throws Exception {
		@SuppressWarnings("deprecation")
		Ruta r = rutaRepository.getById(rutaDto.getId());
		if (r != null) {
			delete(r.getId());
			save(rutaDto, usuario);
		} else {
			throw new Exception("Ruta con id " + rutaDto.getId() + " no encontrada.");
		}
	}
	
	public List<RutaDTO> getRutasDeUsuario(Usuario usuario) {
		List<RutaDTO> ret = null;
		if (usuario.getRutas() != null && !usuario.getRutas().isEmpty()) {
			ret = new ArrayList<RutaDTO>();
			for (Ruta red : usuario.getRutas()) {
				RutaDTO rdto = new RutaDTO();
				rdto.setId(red.getId());
				rdto.setNombre(red.getNombre());
				List<ActividadMerDTO> actividades = new ArrayList<ActividadMerDTO>();
				for (ActividadMer a : red.getPuntosRuta()) {
					actividades.add(actividadMerService.actividadToDto(a));
				}
				ret.add(rdto);
			}
		}
		return ret;
	}
}
