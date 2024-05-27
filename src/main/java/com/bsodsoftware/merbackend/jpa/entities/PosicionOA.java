package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "posicion_oa")
public class PosicionOA implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "x")
	private String x;
	
	@Column(name = "y")
	private String y;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_oahijo")
	private ObjetivoAprendizajeHijo objetivoAprendizajeHijo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public ObjetivoAprendizajeHijo getObjetivoAprendizajeHijo() {
		return objetivoAprendizajeHijo;
	}

	public void setObjetivoAprendizajeHijo(ObjetivoAprendizajeHijo objetivoAprendizajeHijo) {
		this.objetivoAprendizajeHijo = objetivoAprendizajeHijo;
	}
}
