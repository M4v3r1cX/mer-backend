package com.bsodsoftware.merbackend.services.to;


public class ActividadMerDTO {

	private String nombre;
	private String textoCajaOaCapa2;
	private String textoCajaTmCapa2;
	private String ubicacionEnLibro;
	private String descripcionActividad;
	private String imagenReferencia;
	private String linkReferencia;
	private Long idRed;
	private Long idNivel;
	private Long idLibro;
	private Long idUsuarioCarga;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTextoCajaOaCapa2() {
		return textoCajaOaCapa2;
	}
	public void setTextoCajaOaCapa2(String textoCajaOaCapa2) {
		this.textoCajaOaCapa2 = textoCajaOaCapa2;
	}
	public String getTextoCajaTmCapa2() {
		return textoCajaTmCapa2;
	}
	public void setTextoCajaTmCapa2(String textoCajaTmCapa2) {
		this.textoCajaTmCapa2 = textoCajaTmCapa2;
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
	public Long getIdRed() {
		return idRed;
	}
	public void setIdRed(Long idRed) {
		this.idRed = idRed;
	}
	public Long getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(Long idNivel) {
		this.idNivel = idNivel;
	}
	public Long getIdLibro() {
		return idLibro;
	}
	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}
	public Long getIdUsuarioCarga() {
		return idUsuarioCarga;
	}
	public void setIdUsuarioCarga(Long idUsuarioCarga) {
		this.idUsuarioCarga = idUsuarioCarga;
	}
}
