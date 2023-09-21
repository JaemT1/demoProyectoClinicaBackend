package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteDao extends CrudRepository<Paciente,String>{
}
