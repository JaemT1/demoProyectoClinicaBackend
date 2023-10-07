package com.uniquindio.software.clinica.modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Especializacion {


        @Id
        @EqualsAndHashCode.Include

        private String id;
        private String nombre;

}
