package com.djl.service;

import com.djl.domain.Usuario;
import com.djl.dto.UsuarioDTO;
import com.djl.dto.UsuarioGetDTO;
import com.djl.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Usuario insertUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapearEntidad(usuarioDTO);

        Usuario newUsuario = usuarioRepository.save(usuario);
        return newUsuario;
    }

    @Override
    public UsuarioGetDTO getUsuarioById(Integer uid) {
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
        UsuarioGetDTO usuarioDTO = mapearDTOGet(usuario);

        return usuarioDTO;
    }

    //Mapear Entidad a DTO
    private UsuarioDTO mapearDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    private UsuarioGetDTO mapearDTOGet(Usuario usuario) {
        UsuarioGetDTO usuarioDTO = modelMapper.map(usuario, UsuarioGetDTO.class);
        return usuarioDTO;
    }

    //Mapear DTO a Entidad
    private Usuario mapearEntidad(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        return usuario;
    }
}
