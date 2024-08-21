package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class RutaDTO {

	private Long id;
	private List<ActividadMerDTO> actividades;
	private String nombre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<ActividadMerDTO> getActividades() {
		return actividades;
	}
	public void setActividades(List<ActividadMerDTO> actividades) {
		this.actividades = actividades;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
