package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RespuestaPqrs {
    @Id
    @EqualsAndHashCode.Include
    private int idRespuestaPqrs;
    private int idPqrs;
    private String mensaje;
    private int idAdministrador;
    private Date fechaRespuesta;
}
