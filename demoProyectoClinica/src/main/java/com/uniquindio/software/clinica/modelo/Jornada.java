package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Jornada {
    @Id
    @EqualsAndHashCode.Include
    private int id;
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;

}
