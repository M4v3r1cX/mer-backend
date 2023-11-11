package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class LibroRedDTO {
	private Long id;
	private String nombre;
	private List<SubcategoriaDTO> subcategorias;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<SubcategoriaDTO> getSubcategorias() {
		return subcategorias;
	}
	public void setSubcategorias(List<SubcategoriaDTO> subcategorias) {
		this.subcategorias = subcategorias;
	}
	public void addSubcategoria(SubcategoriaDTO subcategoria) {
		if (this.getSubcategorias() == null) {
			this.setSubcategorias(new ArrayList<SubcategoriaDTO>());
		}
		this.getSubcategorias().add(subcategoria);
	}
}
