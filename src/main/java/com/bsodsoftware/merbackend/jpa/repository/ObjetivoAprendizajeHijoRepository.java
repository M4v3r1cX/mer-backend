package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizajeHijo;

public interface ObjetivoAprendizajeHijoRepository extends JpaRepository<ObjetivoAprendizajeHijo, Long> {

	@Query(value = "select oah.* from objetivo_aprendizaje_hijo oah "
			+ "inner join objetivo_aprendizaje oa on oah.id_objetivo_aprendizaje = oa.id "
			+ "inner join oa_hijo_subcategoria ohr on oah.id = ohr.id_oa_hijo "
			+ "inner join subcategoria_red r on ohr.id_subcategoria = r.id "
			+ "inner join oa_nivel oan on oa.id = oan.id_oa "
			+ "inner join nivel n on oan.id_nivel = n.id "
			+ "where n.id = :idNivel and r.id = :idRed", nativeQuery = true)
	public List<ObjetivoAprendizajeHijo> findAllFiltrados(Long idNivel, Long idRed);
}
