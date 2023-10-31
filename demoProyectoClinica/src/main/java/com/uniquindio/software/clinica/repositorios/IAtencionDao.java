package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Atencion;
import org.springframework.data.repository.CrudRepository;

public interface IAtencionDao extends CrudRepository<Atencion, Integer> {
}
