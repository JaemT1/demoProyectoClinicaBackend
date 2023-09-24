package com.uniquindio.software.clinica.modelo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Paciente {
    @Id
    @EqualsAndHashCode.Include
    private String cedula_usuario;
    private Date fecha_nacimiento;
    private String alergias;
    private String eps;
    private String tipo_sangre;
}
