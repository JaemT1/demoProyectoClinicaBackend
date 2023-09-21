package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Examen {
    @Id
    @EqualsAndHashCode.Include
    private int idExamen;
    private String nombre;
    private TipoExamen tipoExamen;
    private String indicaciones;
}
