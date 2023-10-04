package com.uniquindio.software.clinica.modelo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Administrador {
    @Id
    @EqualsAndHashCode.Include
    private int idadministrador;
    private String email;
    private String contrasena;

}
