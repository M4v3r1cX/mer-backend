package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class TMDTO {

	private String id;
	private String descripcion;
	private String idOa;
	private String codigoOa;
	private String descripcionOa;
	private List<String> redes;
	private List<String> niveles;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdOa() {
		return idOa;
	}
	public void setIdOa(String idOa) {
		this.idOa = idOa;
	}
	public String getCodigoOa() {
		return codigoOa;
	}
	public void setCodigoOa(String codigoOa) {
		this.codigoOa = codigoOa;
	}
	public String getDescripcionOa() {
		return descripcionOa;
	}
	public void setDescripcionOa(String descripcionOa) {
		this.descripcionOa = descripcionOa;
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
