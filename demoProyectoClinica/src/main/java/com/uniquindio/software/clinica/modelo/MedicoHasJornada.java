package com.uniquindio.software.clinica.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "medico_has_jornada")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MedicoHasJornada {
    @Id
    @EqualsAndHashCode.Include
    private int id_jornada;
    private String cedula_medico;

    public MedicoHasJornada(int idJornada, String cedulaMedico) {
        this.id_jornada = idJornada;
        this.cedula_medico = cedulaMedico;
    }
    public MedicoHasJornada() {
    }
}
