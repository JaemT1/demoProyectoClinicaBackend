package com.uniquindio.software.clinica.modelo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ausencia {
    @Id
    @EqualsAndHashCode.Include
    private int id;
    private int idJornada;
}
