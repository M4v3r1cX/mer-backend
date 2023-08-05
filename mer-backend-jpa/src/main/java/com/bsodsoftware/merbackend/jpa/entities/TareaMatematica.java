package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tarea_matematica")
public class TareaMatematica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_objetivo_academico")
	private ObjetivoAcademico objetivoAcademico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public ObjetivoAcademico getObjetivoAcademico() {
		return objetivoAcademico;
	}

	public void setObjetivoAcademico(ObjetivoAcademico objetivoAcademico) {
		this.objetivoAcademico = objetivoAcademico;
	}
}
