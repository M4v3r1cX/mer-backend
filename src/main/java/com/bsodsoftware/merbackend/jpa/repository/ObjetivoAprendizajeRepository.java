package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAprendizaje;

public interface ObjetivoAprendizajeRepository extends JpaRepository<ObjetivoAprendizaje, Long> {
	
	@Query(nativeQuery = true, value = "select oa.* from objetivo_aprendizaje oa inner join objetivo_aprendizaje_hijo oh on oa.id = oh.id_objetivo_aprendizaje inner join oa_hijo_subcategoria ohs on oh.id = ohs.id_oa_hijo inner join subcategoria_red s on s.id = ohs.id_subcategoria inner join red r on s.id_red = r.id inner join oa_nivel oan on oa.id = oan.id_oa inner join nivel n on oan.id_nivel = n.id where r.id = :idRed and n.id = :idNivel group by oa.id;")
	public List<ObjetivoAprendizaje> findOasByNivelAndRed(Long idNivel, Long idRed);
	
	@Query(value="select oa from ObjetivoAprendizaje oa where oa.hijos.subcategorias.red.id = :idRed")
	public List<ObjetivoAprendizaje> findOasByRed(Long idRed);
}
