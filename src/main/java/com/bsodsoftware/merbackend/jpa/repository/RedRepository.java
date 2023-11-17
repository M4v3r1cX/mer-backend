package com.bsodsoftware.merbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsodsoftware.merbackend.jpa.entities.Red;

public interface RedRepository extends JpaRepository<Red, Long> {

	Red findOneByNombre(String nombre);
}
