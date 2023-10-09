package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public enum ACCION {
		LOGIN, LOGOUT, OA_CREAR, OA_EDITAR, OA_ELIMINAR, TM_CREAR, TM_EDITAR, TM_ELIMINAR, ACTIVIDAD_CREAR, ACTIVIDAD_EDITAR, ACTIVIDAD_ELIMINAR
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "accion")
	@Enumerated(EnumType.STRING)
	private ACCION accion;
	
	@Column(name = "fecha_hora_evento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraEvento;
	
	@Column(name = "id_usuario")
	private Long idUsuario;
	
	@Column(name = "id_afectado", nullable = true)
	private Long idAfectado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ACCION getAccion() {
		return accion;
	}

	public void setAccion(ACCION accion) {
		this.accion = accion;
	}

	public Date getFechaHoraEvento() {
		return fechaHoraEvento;
	}

	public void setFechaHoraEvento(Date fechaHoraEvento) {
		this.fechaHoraEvento = fechaHoraEvento;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdAfectado() {
		return idAfectado;
	}

	public void setIdAfectado(Long idAfectado) {
		this.idAfectado = idAfectado;
	}
	
}
