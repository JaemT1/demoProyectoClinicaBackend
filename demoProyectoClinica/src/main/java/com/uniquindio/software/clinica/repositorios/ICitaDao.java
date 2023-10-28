package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Cita;
import org.springframework.data.repository.CrudRepository;

public interface ICitaDao extends CrudRepository<Cita, Integer> {
}
