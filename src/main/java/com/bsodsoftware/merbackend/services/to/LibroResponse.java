package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class LibroResponse extends ResponseDTO {
	private List<EntidadGenericaResponseDTO> entidades;

	public List<EntidadGenericaResponseDTO> getEntidades() {
		return entidades;
	}

	public void setEntidades(List<EntidadGenericaResponseDTO> entidades) {
		this.entidades = entidades;
	}
}
