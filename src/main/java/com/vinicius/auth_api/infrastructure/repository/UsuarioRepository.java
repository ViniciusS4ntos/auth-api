package com.vinicius.auth_api.infrastructure.repository;

import com.vinicius.auth_api.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // buscar por email

    Optional<Usuario> findByEmail(String email);

}
