package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class CrearRutaDTO {

	private Long id;
	private List<Long> idsActividades;
	private String nombre;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Long> getIdsActividades() {
		return idsActividades;
	}
	public void setIdsActividades(List<Long> idsActividades) {
		this.idsActividades = idsActividades;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
