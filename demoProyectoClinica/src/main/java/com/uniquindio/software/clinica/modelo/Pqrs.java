package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pqrs {
    @Id
    @EqualsAndHashCode.Include
    private int idPqrs;
    private int idAtencion;
    private Date fechaCreacion;
    private Long numeroRadicado;
    private String cedulaPaciente;
    private EstadoPqrs estadoPqrs;
}
