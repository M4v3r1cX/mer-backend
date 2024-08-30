package com.bsodsoftware.merbackend.services.to;

public class CrearRutaDTO {
	private Long id;
	private String jsonRuta;
	private String nombre;
	
	/*public List<Long> getIdsActividades() {
		return idsActividades;
	}
	public void setIdsActividades(List<Long> idsActividades) {
		this.idsActividades = idsActividades;
	}*/
	public String getNombre() {
		return nombre;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJsonRuta() {
		return jsonRuta;
	}
	public void setJsonRuta(String jsonRuta) {
		this.jsonRuta = jsonRuta;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
