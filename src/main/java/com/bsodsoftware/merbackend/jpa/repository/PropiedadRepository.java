package com.bsodsoftware.merbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsodsoftware.merbackend.jpa.entities.Propiedad;

public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

	public Propiedad findByProp(String prop);
}
