package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cita {
    @Id
    @EqualsAndHashCode.Include
    private int idCita;
    private Date fechaCreacion;
    private Date fechaCita;
    private String cedulaMedico;
    private String cedulaPaciente;
    private Estado estado;
    private String motivo;
}
