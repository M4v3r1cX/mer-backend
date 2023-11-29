package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OaAsociacionDTO {

	private OaDTO oaDto;
	private List<String> asociados;
	
	public OaDTO getOaDto() {
		return oaDto;
	}
	public void setOaDto(OaDTO oaDto) {
		this.oaDto = oaDto;
	}
	public List<String> getAsociados() {
		return asociados;
	}
	public void setAsociados(List<String> asociados) {
		this.asociados = asociados;
	}
	public void addOasAsociados(String oa) {
		if (this.getAsociados() == null) {
			this.setAsociados(new ArrayList<String>());
		}
		this.getAsociados().add(oa);
	}
}
