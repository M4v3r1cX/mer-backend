package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OaHijoDTO {
	private Long id;
	private String descripcion;
	private List<String> redes;
	private List<String> niveles;
	private Boolean prioridad;
	private String x;
	private String y;
	private Boolean tieneCoordenadas;
	
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
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public Boolean getTieneCoordenadas() {
		return tieneCoordenadas;
	}
	public void setTieneCoordenadas(Boolean tieneCoordenadas) {
		this.tieneCoordenadas = tieneCoordenadas;
	}
}