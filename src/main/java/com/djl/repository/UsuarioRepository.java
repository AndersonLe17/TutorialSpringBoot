package com.djl.repository;

import com.djl.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Boolean existsByCorreoAndId(String correo, Integer id);
    Boolean existsByCorreo(String correo);

}
