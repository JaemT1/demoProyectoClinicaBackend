package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fechaCreacion;
    private Time horaCreacion;
    private Date fechaCita;
    private Time horaCita;
    private String cedulaMedico;
    private String cedulaPaciente;
    private String estado;
    private String motivo;

    public Cita(Date fechaCreacion, Time horaCreacion, Date fechaCita, Time horaCita, String cedulaMedico, String cedulaPaciente, String estado, String motivo) {
        this.fechaCreacion = fechaCreacion;
        this.horaCreacion = horaCreacion;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.cedulaMedico = cedulaMedico;
        this.cedulaPaciente = cedulaPaciente;
        this.estado = estado;
        this.motivo = motivo;
    }

    public Cita() {

    }
}
