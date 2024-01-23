package com.djl.controller;

import com.djl.domain.Usuario;
import com.djl.dto.request.UsuarioRequest;
import com.djl.dto.response.UsuarioResponse;
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
    private UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<?> insertUsuario(@RequestBody UsuarioRequest usuarioDTO){
        UsuarioResponse newUsuario = usuarioService.insertUsuario(usuarioDTO).orElseThrow(() -> new RuntimeException("Correo ya existente"));

        return ResponseEntity.ok(newUsuario);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getUsuarioById(@PathVariable(name = "uid") Integer uid) {
        UsuarioResponse usuario = usuarioService.getUsuarioById(uid);

        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.findAllUsuarios();

        return ResponseEntity.ok(usuarios);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteUsuarioById(@PathVariable(name = "uid") Integer uid) {
        String respuesta = usuarioService.deleteUsuario(uid);

        return ResponseEntity.ok(respuesta);
    }

    @PutMapping("/{uid}")
    public ResponseEntity<?> updatedUsuario(@RequestBody UsuarioRequest usuarioDTO,
                                            @PathVariable(name = "uid") Integer uid) {
        UsuarioResponse updatedUsuario = usuarioService.updatedUsuario(usuarioDTO, uid);

        return ResponseEntity.ok(updatedUsuario == null? "No se puedo modificar" : updatedUsuario);
    }

}
