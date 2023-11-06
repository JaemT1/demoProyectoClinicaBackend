package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Atencion;
import com.uniquindio.software.clinica.modelo.Cita;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAtencionDao extends CrudRepository<Atencion, Integer> {
    @Query("SELECT a FROM Atencion a JOIN Cita c ON a.idCita = c.id AND c.cedulaMedico = ?1")
    List<Atencion> obtenerAtencionesPorMedico(String cedulaMedico);

    @Query("SELECT a FROM Atencion a JOIN Cita c ON a.idCita = c.id AND c.cedulaPaciente = ?1")
    List<Atencion> obtenerAtencionesPorPaciente(String cedulaPaciente);
}
