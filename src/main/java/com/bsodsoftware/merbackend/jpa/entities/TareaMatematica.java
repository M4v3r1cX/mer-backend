package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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
	@JoinColumn(name = "id_objetivo_aprendizaje_hijo")
	private ObjetivoAprendizajeHijo objetivoAprendizajeHijo;
	
	@Column(name = "id_usuario")
	private Long idUsuario;

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

	public ObjetivoAprendizajeHijo getObjetivoAprendizajeHijo() {
		return objetivoAprendizajeHijo;
	}

	public void setObjetivoAprendizajeHijo(ObjetivoAprendizajeHijo objetivoAprendizajeHijo) {
		this.objetivoAprendizajeHijo = objetivoAprendizajeHijo;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
}
