package com.uniquindio.software.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private String cedula;
    private String nombre;
    private String contrasena;
    private String email;
    private String telefono;
    private String ciudad;
    private String url_foto;
}
