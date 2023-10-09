package com.bsodsoftware.merbackend.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Auditoria;
import com.bsodsoftware.merbackend.jpa.repository.AuditoriaRepository;

@Service
public class AuditoriaService {

	@Autowired
	private AuditoriaRepository auditoriaRepository;
	
	public void guardarAccion(Auditoria.ACCION accion, Long idUsuario) {
		Auditoria auditoria = new Auditoria();
		auditoria.setAccion(accion);
		auditoria.setIdUsuario(idUsuario);
		auditoria.setFechaHoraEvento(new Date());
		auditoriaRepository.saveAndFlush(auditoria);
	}
}
