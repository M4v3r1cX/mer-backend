package com.bsodsoftware.merbackend.jpa.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "actividad_mer")
public class ActividadMer implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "texto_caja_oa_capa_2")
	private String textoCajaOaCapa2;
	
	@Column(name = "texto_caja_tm_capa_2")
	private String textoCajaTmCapa2;
	
	@Column(name = "ubicacion_en_libro")
	private String ubicacionEnLibro;
	
	@Column(name = "descripcion_actividad")
	private String descripcionActividad;
	
	@Lob
	@Column(name = "imagen_referencia", length=65535)
	private String imagenReferencia;
	
	@Column(name = "link_referencia")
	private String linkReferencia;
	
	@Column(name = "id_red")
	private Long idRed;
	
	@Column(name = "id_nivel")
	private Long idNivel;
	
	@Column(name = "id_libro")
	private Long idLibro;
	
	@Column(name = "id_usuario_carga")
	private Long idUsuarioCarga;
	
	@Column(name = "fecha_hora_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraCreacion;
	
	@Column(name = "fecha_hora_modificacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHoraModificacion;

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

	public Date getFechaHoraCreacion() {
		return fechaHoraCreacion;
	}

	public void setFechaHoraCreacion(Date fechaHoraCreacion) {
		this.fechaHoraCreacion = fechaHoraCreacion;
	}

	public Date getFechaHoraModificacion() {
		return fechaHoraModificacion;
	}

	public void setFechaHoraModificacion(Date fechaHoraModificacion) {
		this.fechaHoraModificacion = fechaHoraModificacion;
	}

	public String getTextoCajaOaCapa2() {
		return textoCajaOaCapa2;
	}

	public void setTextoCajaOaCapa2(String textoCajaOaCapa2) {
		this.textoCajaOaCapa2 = textoCajaOaCapa2;
	}

	public String getTextoCajaTmCapa2() {
		return textoCajaTmCapa2;
	}

	public void setTextoCajaTmCapa2(String textoCajaTmCapa2) {
		this.textoCajaTmCapa2 = textoCajaTmCapa2;
	}

	public String getUbicacionEnLibro() {
		return ubicacionEnLibro;
	}

	public void setUbicacionEnLibro(String ubicacionEnLibro) {
		this.ubicacionEnLibro = ubicacionEnLibro;
	}

	public String getDescripcionActividad() {
		return descripcionActividad;
	}

	public void setDescripcionActividad(String descripcionActividad) {
		this.descripcionActividad = descripcionActividad;
	}

	public String getImagenReferencia() {
		return imagenReferencia;
	}

	public void setImagenReferencia(String imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

	public String getLinkReferencia() {
		return linkReferencia;
	}

	public void setLinkReferencia(String linkReferencia) {
		this.linkReferencia = linkReferencia;
	}

	public Long getIdRed() {
		return idRed;
	}

	public void setIdRed(Long idRed) {
		this.idRed = idRed;
	}

	public Long getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(Long idNivel) {
		this.idNivel = idNivel;
	}

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public Long getIdUsuarioCarga() {
		return idUsuarioCarga;
	}

	public void setIdUsuarioCarga(Long idUsuarioCarga) {
		this.idUsuarioCarga = idUsuarioCarga;
	}
}
