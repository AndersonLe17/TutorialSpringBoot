package com.djl.service;

import com.djl.domain.Usuario;
import com.djl.dto.UsuarioDTO;
import com.djl.dto.UsuarioGetDTO;

public interface UsuarioService {
    Usuario insertUsuario(UsuarioDTO usuarioDTO);

    UsuarioGetDTO getUsuarioById(Integer uid);
}
