package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OaHijoDTO {
	private String descripcion;
	private List<String> redes;
	private List<String> niveles;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getRedes() {
		return redes;
	}
	public void setRedes(List<String> redes) {
		this.redes = redes;
	}
	public List<String> getNiveles() {
		return niveles;
	}
	public void setNiveles(List<String> niveles) {
		this.niveles = niveles;
	}
	public void addRed(String r) {
		if (this.getRedes() == null) {
			this.setRedes(new ArrayList<String>());
		}
		this.getRedes().add(r);
	}
	public void addNivel(String n) {
		if (this.getNiveles() == null) {
			this.setNiveles(new ArrayList<String>());
		}
		this.getNiveles().add(n);
	}
}