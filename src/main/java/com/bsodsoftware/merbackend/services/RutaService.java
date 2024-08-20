package com.bsodsoftware.merbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.repository.RutaRepository;

@Service
public class RutaService {

	@Autowired
	private RutaRepository rutaRepository;
}
