package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class LibroRedResponse {
	private List<LibroRedDTO> libros;
	
	public List<LibroRedDTO> getLibros() {
		return libros;
	}
	public void setLibros(List<LibroRedDTO> libros) {
		this.libros = libros;
	}
}
