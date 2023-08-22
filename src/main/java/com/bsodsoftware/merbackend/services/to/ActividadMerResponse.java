package com.bsodsoftware.merbackend.services.to;

import java.util.List;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;

public class ActividadMerResponse extends ResponseDTO {
	private List<ActividadMerListaDto> actividades;

	public List<ActividadMerListaDto> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadMerListaDto> actividades) {
		this.actividades = actividades;
	}
}
