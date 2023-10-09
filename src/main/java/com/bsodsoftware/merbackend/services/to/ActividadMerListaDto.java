package com.bsodsoftware.merbackend.services.to;

import java.util.ArrayList;
import java.util.List;

public class ActividadMerListaDto {

	private Long id;
	private String nombre;
	private String libro;
	private String tm;
	private String oa;
	private List<String> redes;
	private List<String> niveles;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getOa() {
		return oa;
	}
	public void setOa(String oa) {
		this.oa = oa;
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
