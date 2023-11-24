package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class AsociarOaDTO {
	private String idOaInicial;
	private List<String> idOasFinales;
	
	public String getIdOaInicial() {
		return idOaInicial;
	}
	public void setIdOaInicial(String idOaInicial) {
		this.idOaInicial = idOaInicial;
	}
	public List<String> getIdOasFinales() {
		return idOasFinales;
	}
	public void setIdOasFinales(List<String> idOasFinales) {
		this.idOasFinales = idOasFinales;
	}
}
