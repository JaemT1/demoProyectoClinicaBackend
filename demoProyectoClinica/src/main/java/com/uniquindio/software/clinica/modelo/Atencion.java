package com.uniquindio.software.clinica.modelo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion {
    @Id
    @EqualsAndHashCode.Include
    private int idAtencion;
    private int idCita;
    private String sintomas;
    private String diagnostico;
    private String notaMedicas;
    private int idTratamiento;
}
