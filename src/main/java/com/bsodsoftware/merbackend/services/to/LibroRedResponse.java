package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class LibroRedResponse {
	private List<LibroRedDTO> libros;
	private List<LibroRedDTO> redes;
	
	public List<LibroRedDTO> getLibros() {
		return libros;
	}
	public void setLibros(List<LibroRedDTO> libros) {
		this.libros = libros;
	}
	public List<LibroRedDTO> getRedes() {
		return redes;
	}
	public void setRedes(List<LibroRedDTO> redes) {
		this.redes = redes;
	}
}
