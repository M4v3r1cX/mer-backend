package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;
import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;

public interface ObjetivoAprendizajeHijoRepository extends JpaRepository<ObjetivoAprendizajeHijo, Long> {

	@Query(value="select h from ObjetivoAprendizajeHijo h "
			+ "join h.posicionOa po "
			+ "join h.subcategorias s "
			+ "join s.red r "
			+ "where r.id = :idRed "
			+ "and po is not null")
	public List<ObjetivoAprendizajeHijo> findOaHijosByRed(Long idRed);
}
