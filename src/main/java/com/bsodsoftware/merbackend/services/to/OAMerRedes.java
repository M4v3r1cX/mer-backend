package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OAMerRedes {

	private Long nivel;
	private List<OAMerRedesOA> oas;
	
	public Long getNivel() {
		return nivel;
	}
	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}
	public List<OAMerRedesOA> getOas() {
		return oas;
	}
	public void setOas(List<OAMerRedesOA> oas) {
		this.oas = oas;
	}
	public void addOa(OAMerRedesOA oa) {
		if (this.getOas() == null) {
			this.setOas(new ArrayList<OAMerRedesOA>());
		}
		this.getOas().add(oa);
	}
}
