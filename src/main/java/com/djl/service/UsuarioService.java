package com.djl.service;

import com.djl.dto.request.UsuarioRequest;
import com.djl.dto.response.UsuarioResponse;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<UsuarioResponse> insertUsuario(UsuarioRequest usuarioDTO);

    UsuarioResponse getUsuarioById(Integer uid);

    List<UsuarioResponse> findAllUsuarios();

    String deleteUsuario(Integer uid);

    UsuarioResponse updatedUsuario(UsuarioRequest usuarioDTO, Integer uid);
}
