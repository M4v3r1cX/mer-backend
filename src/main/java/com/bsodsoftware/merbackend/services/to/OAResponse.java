package com.bsodsoftware.merbackend.services.to;

import java.util.List;

public class OAResponse extends ResponseDTO {
	private List<OaDTO> oas;

	public List<OaDTO> getOas() {
		return oas;
	}

	public void setOas(List<OaDTO> oas) {
		this.oas = oas;
	}
}
