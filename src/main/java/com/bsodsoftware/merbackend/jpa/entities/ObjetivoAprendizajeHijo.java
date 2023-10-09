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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "objetivo_aprendizaje_hijo")
public class ObjetivoAprendizajeHijo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	@JoinTable(
		name = "oa_hijo_red",
		joinColumns = @JoinColumn(name = "id_oa_hijo"),
		inverseJoinColumns = @JoinColumn(name  = "id_red"))
	private List<Red> redes;
	
	@ManyToMany
	@JoinTable(
		name = "oa_hijo_nivel",
		joinColumns = @JoinColumn(name = "id_oa_hijo"),
		inverseJoinColumns = @JoinColumn(name  = "id_nivel"))
	private List<Nivel> niveles;
	
	@ManyToOne
	@JoinColumn(name = "id_objetivo_aprendizaje")
	private ObjetivoAprendizaje objetivoAprendizaje;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToMany(mappedBy = "objetivoAprendizajeHijo", fetch = FetchType.LAZY)
	private List<TareaMatematica> tareasMatematicas;
	
	@Column(name = "priorizado")
	private Boolean priorizado;

	public List<TareaMatematica> getTareasMatematicas() {
		return tareasMatematicas;
	}

	public void setTareasMatematicas(List<TareaMatematica> tareasMatematicas) {
		this.tareasMatematicas = tareasMatematicas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Red> getRedes() {
		return redes;
	}

	public void setRedes(List<Red> redes) {
		this.redes = redes;
	}

	public List<Nivel> getNiveles() {
		return niveles;
	}

	public void setNiveles(List<Nivel> niveles) {
		this.niveles = niveles;
	}

	public ObjetivoAprendizaje getObjetivoAprendizaje() {
		return objetivoAprendizaje;
	}

	public void setObjetivoAprendizaje(ObjetivoAprendizaje objetivoAprendizaje) {
		this.objetivoAprendizaje = objetivoAprendizaje;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public void addRed(Red red) {
		if (this.getRedes() == null) {
			setRedes(new ArrayList<Red>());
		}
		this.getRedes().add(red);
	}
	
	public void addNivel(Nivel nivel) {
		if (this.getNiveles() == null) {
			setNiveles(new ArrayList<Nivel>());
		}
		this.getNiveles().add(nivel);
	}
	
	public void addTareaMatematica(TareaMatematica tm) {
		if (this.getTareasMatematicas() == null) {
			setTareasMatematicas(new ArrayList<TareaMatematica>());
		}
		this.getTareasMatematicas().add(tm);
	}

	public Boolean isPriorizado() {
		return priorizado;
	}

	public void setPriorizado(Boolean priorizado) {
		this.priorizado = priorizado;
	}
}
