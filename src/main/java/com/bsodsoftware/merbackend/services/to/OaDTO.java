package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class OaDTO {

	private String id;
	private String codigo;
	private String descripcion;
	private Boolean prioridad;
	private List<String> redes;
	private List<String> niveles;
	private List<OaHijoDTO> hijos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
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
	public List<OaHijoDTO> getHijos() {
		return hijos;
	}
	public void setHijos(List<OaHijoDTO> hijos) {
		this.hijos = hijos;
	}
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
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
	public void addHijo(OaHijoDTO hijo) {
		if (this.getHijos() == null) {
			this.setHijos(new ArrayList<OaHijoDTO>());
		}
		this.getHijos().add(hijo);
	}
}
