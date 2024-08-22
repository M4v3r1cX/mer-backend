package com.bsodsoftware.merbackend.services.to;


public class ActividadMerDTO {

	private String nombre;
	private String ubicacionEnLibro;
	private String descripcionActividad;
	private String imagenReferencia;
	private String linkReferencia;
	private String idLibro;
	private String idUsuarioCarga;
	private String id;
	private String idTm;
	private Boolean seleccionado;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacionEnLibro() {
		return ubicacionEnLibro;
	}
	public void setUbicacionEnLibro(String ubicacionEnLibro) {
		this.ubicacionEnLibro = ubicacionEnLibro;
	}
	public String getDescripcionActividad() {
		return descripcionActividad;
	}
	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}
	public String getImagenReferencia() {
		return imagenReferencia;
	}
	public void setImagenReferencia(String imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}
	public String getLinkReferencia() {
		return linkReferencia;
	}
	public void setLinkReferencia(String linkReferencia) {
		this.linkReferencia = linkReferencia;
	}
	public String getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(String idLibro) {
		this.idLibro = idLibro;
	}
	public String getIdUsuarioCarga() {
		return idUsuarioCarga;
	}
	public void setIdUsuarioCarga(String idUsuarioCarga) {
		this.idUsuarioCarga = idUsuarioCarga;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdTm() {
		return idTm;
	}
	public void setIdTm(String idTm) {
		this.idTm = idTm;
	}
	public Boolean getSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(Boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
