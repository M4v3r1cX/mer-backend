package com.bsodsoftware.merbackend.jpa.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ruta")
public class Ruta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "json_ruta", columnDefinition="TEXT")
	private String jsonRuta;
	
	@ManyToMany
	@JoinTable(
		name = "punto_ruta",
		joinColumns = @JoinColumn(name = "id_ruta"),
		inverseJoinColumns = @JoinColumn(name  = "id_actividad_mer"))
	private List<ActividadMer> puntosRuta;
	
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

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

	public List<ActividadMer> getPuntosRuta() {
		return puntosRuta;
	}

	public void setPuntosRuta(List<ActividadMer> puntosRuta) {
		this.puntosRuta = puntosRuta;
	}
	
	public void addPuntoRuta(ActividadMer actividadMer) {
		if (this.getPuntosRuta() == null) {
			this.setPuntosRuta(new ArrayList<ActividadMer>());
		}
		this.getPuntosRuta().add(actividadMer);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getJsonRuta() {
		return jsonRuta;
	}

	public void setJsonRuta(String jsonRuta) {
		this.jsonRuta = jsonRuta;
	}
}
