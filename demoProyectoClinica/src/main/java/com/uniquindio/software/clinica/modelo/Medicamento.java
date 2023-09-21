package com.uniquindio.software.clinica.modelo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medicamento {
    @Id
    @EqualsAndHashCode.Include
    private int idMedicamento;
    private String nombre;
    private String posologia;
}
