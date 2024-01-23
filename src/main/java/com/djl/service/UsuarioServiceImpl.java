package com.djl.service;

import com.djl.domain.Usuario;
import com.djl.dto.request.UsuarioRequest;
import com.djl.dto.response.UsuarioResponse;
import com.djl.repository.UsuarioRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<UsuarioResponse> insertUsuario(UsuarioRequest usuarioDTO) {
        Boolean exist = usuarioRepository.existsByCorreo(usuarioDTO.getCorreo());
        if (exist) return Optional.empty();

        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class); // Se mapea un usuario con los datos del DTO
        usuario = usuarioRepository.save(usuario);
        UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);

        return Optional.of(response);
    }

    @Override
    public UsuarioResponse getUsuarioById(Integer uid) {
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
        UsuarioResponse usuarioDTO = modelMapper.map(usuario, UsuarioResponse.class);

        return usuarioDTO;
    }

    @Override
    public List<UsuarioResponse> findAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioResponse> usuarioResponses = usuarios.stream().map(u -> modelMapper.map(u, UsuarioResponse.class)).toList();

        return usuarioResponses;
    }

    @Override
    public String deleteUsuario(Integer uid) {
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
        usuarioRepository.delete(usuario);

        return "Usuario Eliminado";
    }

    @Override
    public UsuarioResponse updatedUsuario(UsuarioRequest usuarioDTO, Integer uid) {
//        Boolean exists = usuarioRepository.existsById(uid);
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
//        if (!exists) return null;

        Usuario updateUsuario;

        if (usuarioDTO.getCorreo() != null && usuarioRepository.existsByCorreo(usuarioDTO.getCorreo())) {
            if (usuarioRepository.existsByCorreoAndId(usuarioDTO.getCorreo(), uid)) {
                updateUsuario = modelMapper.map(usuarioDTO, Usuario.class);
                updateUsuario.setId(uid);
            } else {
                return null; // Ese correo ya le pertenece a otro usuario
            }
        } else {
            modelMapper.map(usuarioDTO, usuario);
//            updateUsuario.setId(uid);
        }

        usuarioRepository.save(usuario);
        UsuarioResponse response = modelMapper.map(usuario, UsuarioResponse.class);
        return response;
    }

}
