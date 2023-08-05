package com.bsodsoftware.merbackend.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
