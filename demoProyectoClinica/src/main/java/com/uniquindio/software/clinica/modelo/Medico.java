package com.uniquindio.software.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medico {
   @Id
   @EqualsAndHashCode.Include
    private String cedula_usuario;
    private String especializacion;

    public Medico() {}
}
