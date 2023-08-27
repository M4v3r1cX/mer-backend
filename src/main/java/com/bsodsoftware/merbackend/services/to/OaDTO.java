package com.bsodsoftware.merbackend.services.to;

public class OaDTO {

	private String id;
	private String nombre;
	private String descripcion;
	private String idRed;
	private String idNivel;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdRed() {
		return idRed;
	}
	public void setIdRed(String idRed) {
		this.idRed = idRed;
	}
	public String getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(String idNivel) {
		this.idNivel = idNivel;
	}
}
