package com.bsodsoftware.merbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;
import com.bsodsoftware.merbackend.jpa.entities.PosicionOA;
import com.bsodsoftware.merbackend.jpa.repository.PosicionOARepository;
import com.bsodsoftware.merbackend.services.to.PosicionOADTO;
import com.bsodsoftware.merbackend.services.to.ResponseDTO;

@Service
public class PosicionOAService {

	@Autowired
	private PosicionOARepository repository;
	
	@Autowired
	private ObjetivoAprendizajeService objetivoAprendizajeHijoService;
	
	public PosicionOA save(PosicionOA entity) {
		return repository.save(entity);
	}

	public Optional<PosicionOA> findById(Long id) {
		return repository.findById(id);
	}

	public ResponseDTO guardarPosicion(PosicionOADTO posicionOaDTO) {
		ResponseDTO ret = new ResponseDTO();
		
		ObjetivoAprendizajeHijo oaHijo = objetivoAprendizajeHijoService.findOaHijoById(posicionOaDTO.getId());
		if (oaHijo != null) {
			PosicionOA pos = oaHijo.getPosicionOa();
			if (pos == null) {
				pos = new PosicionOA();
				pos.setObjetivoAprendizajeHijo(oaHijo);
			}
			pos.setX(posicionOaDTO.getX());
			pos.setY(posicionOaDTO.getY());
			save(pos);
			ret.setCodigo(200);
			ret.setComentario("Posicion Guardada Correctamente");
		} else {
			ret.setCodigo(500);
			ret.setComentario("OAHijo id " + posicionOaDTO.getId() + " no encontrado.");
		}
		
		return ret;
	}
}
