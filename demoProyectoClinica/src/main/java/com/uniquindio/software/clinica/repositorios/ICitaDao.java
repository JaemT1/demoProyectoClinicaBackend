package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Cita;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface ICitaDao extends CrudRepository<Cita, Integer> {
    List<Cita> findByFechaCita(Date fecha_cita);
}
