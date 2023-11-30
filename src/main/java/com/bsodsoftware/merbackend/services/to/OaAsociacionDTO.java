package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OaAsociacionDTO {

	private OaDTO oaDto;
	private List<OaDTO> asociados;
	
	public OaDTO getOaDto() {
		return oaDto;
	}
	public void setOaDto(OaDTO oaDto) {
		this.oaDto = oaDto;
	}
	public List<OaDTO> getAsociados() {
		return asociados;
	}
	public void setAsociados(List<OaDTO> asociados) {
		this.asociados = asociados;
	}
	public void addOasAsociados(OaDTO oa) {
		if (this.getAsociados() == null) {
			this.setAsociados(new ArrayList<OaDTO>());
		}
		this.getAsociados().add(oa);
	}
}
