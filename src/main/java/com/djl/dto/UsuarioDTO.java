package com.djl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UsuarioDTO { // Data Transfer Object
    private String nombre;
    private String correo;
    private String password;
    private Date fechaNacimiento;
    private String celular;
}
