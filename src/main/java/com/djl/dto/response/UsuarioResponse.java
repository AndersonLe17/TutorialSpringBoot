package com.djl.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UsuarioResponse {
    private Integer id;
    private String nombre;
    private String correo;
    private Date fechaNacimiento;
    private String celular;
}
