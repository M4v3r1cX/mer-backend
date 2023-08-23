package com.bsodsoftware.merbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.bsodsoftware.merbackend.jpa.entities.Libro;
import com.bsodsoftware.merbackend.jpa.entities.Red;

import jakarta.annotation.PostConstruct;

@Service
public class InitService {
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	RedService redService;

	@EventListener(ContextRefreshedEvent.class)
	public void initData() {
		long count = libroService.count();
		if (count == 0) {
			List<Libro> libros = new ArrayList<Libro>();
			libros.add(new Libro("Sumo Primero",""));
			libros.add(new Libro("Cuaderno Rural",""));
			libros.add(new Libro("Clase Publica",""));
			libros.add(new Libro("Juego",""));
			libroService.save(libros);
		}
		
		long count2 = redService.count();
		if (count2 == 0) {
			List<Red> redes = new ArrayList<Red>();
			redes.add(new Red("Números",""));
			redes.add(new Red("Campo Aditivo",""));
			redes.add(new Red("Campo Multiplicativo",""));
			redes.add(new Red("Patrones y Álgebra",""));
			redes.add(new Red("Medición",""));
			redes.add(new Red("Geometría",""));
			redes.add(new Red("Datos y Probabilidades",""));
			redService.saveAll(redes);
		}
	}
}
