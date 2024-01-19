package com.djl.controller;

import com.djl.domain.Usuario;
import com.djl.dto.UsuarioDTO;
import com.djl.dto.UsuarioGetDTO;
import com.djl.repository.UsuarioRepository;
import com.djl.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<?> insertUsuario(@RequestBody UsuarioDTO usuarioDTO){
        Usuario newUsuario = usuarioService.insertUsuario(usuarioDTO);

        return ResponseEntity.ok(newUsuario);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getUsuarioById(@PathVariable(name = "uid") Integer uid) {
        UsuarioGetDTO usuario = usuarioService.getUsuarioById(uid);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteUsuarioById(@PathVariable(name = "uid") Integer uid) {
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
        usuarioRepository.delete(usuario);

        return ResponseEntity.ok("Usuario eliminado");
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> updatedUsuario(@RequestBody UsuarioDTO usuarioDTO,
                                           @PathVariable(name = "uid") Integer uid) {
        Usuario usuario = usuarioRepository.findById(uid).orElseThrow();
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCelular(usuarioDTO.getCelular());

        Usuario updatedUsuario = usuarioRepository.save(usuario);

        return ResponseEntity.ok(updatedUsuario);
    }

}
