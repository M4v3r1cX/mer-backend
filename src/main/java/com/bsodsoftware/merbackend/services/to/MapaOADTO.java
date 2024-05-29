package com.bsodsoftware.merbackend.services.to;

public class MapaOADTO {

	private String id;
	private String codigo;
	private String descripcion;
	private Boolean prioridad;
	private Long idNivel;
	private Long idSubcategoria;
	private Boolean tienePosicionamiento;
	private String x;
	private String y;
	
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
	public Boolean getPrioridad() {
		return prioridad;
	}
	public void setPrioridad(Boolean prioridad) {
		this.prioridad = prioridad;
	}
	public Long getIdNivel() {
		return idNivel;
	}
	public void setIdNivel(Long idNivel) {
		this.idNivel = idNivel;
	}
	public Long getIdSubcategoria() {
		return idSubcategoria;
	}
	public void setIdSubcategoria(Long idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}
	public Boolean getTienePosicionamiento() {
		return tienePosicionamiento;
	}
	public void setTienePosicionamiento(Boolean tienePosicionamiento) {
		this.tienePosicionamiento = tienePosicionamiento;
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
}
