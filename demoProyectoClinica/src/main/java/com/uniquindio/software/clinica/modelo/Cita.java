package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @Id
    @EqualsAndHashCode.Include
    private int idCita;
    private Date fechaCreacion;
    private Date fechaCita;
    private LocalTime hora;
    private String cedulaMedico;
    private String cedulaPaciente;
    private Estado estado;
    private String motivo;

    public Cita(){}
}
