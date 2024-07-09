package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.ActividadMer;

public interface ActividadMerRepository extends JpaRepository<ActividadMer, Long> {

	@Query(value="select a from ActividadMer a join a.tareaMatematica tm where tm.id = :idTareaMatematica")
	public List<ActividadMer> findActividadesByTareaMatematica(Long idTareaMatematica);
}
