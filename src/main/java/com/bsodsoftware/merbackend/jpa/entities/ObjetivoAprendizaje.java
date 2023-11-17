package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;
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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "objetivo_aprendizaje")
public class ObjetivoAprendizaje implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	/*@ManyToMany
	@JoinTable(
		name = "oa_red",
		joinColumns = @JoinColumn(name = "id_oa"),
		inverseJoinColumns = @JoinColumn(name  = "id_red"))
	private List<Red> redes;*/
	
	@ManyToMany
	@JoinTable(
		name = "oa_subcategoria",
		joinColumns = @JoinColumn(name = "id_oa"),
		inverseJoinColumns = @JoinColumn(name  = "id_subcategoria"))
	private List<SubcategoriaRed> subcategorias;
	
	@ManyToMany
	@JoinTable(
		name = "oa_nivel",
		joinColumns = @JoinColumn(name = "id_oa"),
		inverseJoinColumns = @JoinColumn(name  = "id_nivel"))
	private List<Nivel> niveles;
	
	@Column(name = "priorizado")
	private boolean priorizado;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@OneToMany(mappedBy = "objetivoAprendizaje", fetch = FetchType.LAZY)
	private List<ObjetivoAprendizajeHijo> hijos;
	
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

	public List<SubcategoriaRed> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<SubcategoriaRed> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public List<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public void addSubcategoria(SubcategoriaRed subcategoria) {
		if (this.getSubcategorias() == null) {
			setSubcategorias(new ArrayList<SubcategoriaRed>());
		}
		this.getSubcategorias().add(subcategoria);
	}
	
	public void addNivel(Nivel nivel) {
		if (this.getNiveles() == null) {
			setNiveles(new ArrayList<Nivel>());
		}
		this.getNiveles().add(nivel);
	}

	public boolean isPriorizado() {
		return priorizado;
	}

	public void setPriorizado(boolean priorizado) {
		this.priorizado = priorizado;
	}

	public List<ObjetivoAprendizajeHijo> getHijos() {
		return hijos;
	}

	public void setHijos(List<ObjetivoAprendizajeHijo> hijos) {
		this.hijos = hijos;
	}
	
	public void addHijo(ObjetivoAprendizajeHijo hijo) {
		if (this.getHijos() == null) {
			this.setHijos(new ArrayList<ObjetivoAprendizajeHijo>());
		}
		this.getHijos().add(hijo);
	}
}
