package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Cita;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaDao extends CrudRepository<Cita, Integer> {
    List<Cita> findByFechaCita(Date fecha_cita);

    @Query("SELECT c FROM Cita c WHERE c.estado = 'Agendada' ")
    List<Cita> obtenerCitasProximas();

    @Query("SELECT c FROM Cita c WHERE c.estado = 'Cancelada' OR c.estado = 'Completada' ")
    List<Cita> obtenerCitasAnteriores();

}
