package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "red")
public class Red implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "red")
	private List<SubcategoriaRed> subcategoriaRed;
	
	public Red() {
		
	}
	
	public Red(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SubcategoriaRed> getSubcategoriaRed() {
		return subcategoriaRed;
	}

	public void setSubcategoriaRed(List<SubcategoriaRed> subcategoriaRed) {
		this.subcategoriaRed = subcategoriaRed;
	}
	
	public void addSubcategoriaRed(SubcategoriaRed subcategoriaRed) {
		if (this.getSubcategoriaRed() == null) {
			this.setSubcategoriaRed(new ArrayList<SubcategoriaRed>());
		}
		this.getSubcategoriaRed().add(subcategoriaRed);
	}
}
