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
    private String cedulaPaciente;
    private Date fechaNacimiento;
    private String alergias;
    private String eps;
    private TipoSangre tipoSangre;
}
