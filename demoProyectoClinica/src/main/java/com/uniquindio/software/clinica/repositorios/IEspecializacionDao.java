package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Especializacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEspecializacionDao extends CrudRepository<Especializacion,String> {
}
