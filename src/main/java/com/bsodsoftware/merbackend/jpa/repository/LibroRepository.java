package com.bsodsoftware.merbackend.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bsodsoftware.merbackend.jpa.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
