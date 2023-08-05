package com.bsodsoftware.merbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsodsoftware.merbackend.jpa.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
