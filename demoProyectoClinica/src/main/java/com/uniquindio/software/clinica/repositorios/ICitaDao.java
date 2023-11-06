package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Cita;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaDao extends CrudRepository<Cita, Integer> {
    List<Cita> findByFechaCita(Date fecha_cita);

    @Query("SELECT c FROM Cita c WHERE c.estado = 'Agendada' AND c.cedulaPaciente = ?1")
    List<Cita> obtenerCitasProximasPacienteEsp(String cedula_paciente);

    @Query("SELECT c FROM Cita c WHERE c.estado = 'Agendada'")
    List<Cita> obtenerCitasProximas();

    @Query("SELECT c FROM Cita c WHERE c.estado = 'Cancelada' OR c.estado = 'Completada' AND c.cedulaPaciente = ?1")
    List<Cita> obtenerCitasAnteriores(String cedula_paciente);

    @Modifying
    @Query("UPDATE Cita c SET c.estado = ?1 WHERE c.id = ?2")
    void cambiarEstado(String estado, int id);
}
