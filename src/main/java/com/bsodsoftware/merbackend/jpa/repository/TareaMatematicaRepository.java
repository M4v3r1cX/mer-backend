package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;

public interface TareaMatematicaRepository extends JpaRepository<TareaMatematica, Long> {

	@Query(value = "select tm.* from tarea_matematica tm " 
			+ "inner join objetivo_aprendizaje_hijo oah on tm.id_objetivo_aprendizaje_hijo = oah.id "
			+ "inner join objetivo_aprendizaje oa on oah.id_objetivo_aprendizaje = oa.id "
			+ "inner join oa_hijo_subcategoria ohr on oah.id = ohr.id_oa_hijo "
			+ "inner join subcategoria_red sr on ohr.id_subcategoria = sr.id "
			+ "inner join red r on sr.id_red = r.id "
			+ "inner join oa_nivel oan on oa.id = oan.id_oa "
			+ "inner join nivel n on oan.id_nivel = n.id "
			+ "where n.id = :idNivel and r.id = :idRed", nativeQuery = true)
	public List<TareaMatematica> findAllFiltrados(Long idNivel, Long idRed);
}
