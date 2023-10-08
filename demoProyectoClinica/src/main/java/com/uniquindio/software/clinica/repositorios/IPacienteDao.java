package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Paciente;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteDao extends CrudRepository<Paciente,String>{
    @Modifying
    @Query("UPDATE Paciente p SET p.alergias = ?1, p.eps = ?2 WHERE p.cedula_usuario = ?3")
    void editarPaciente(String alergias, String eps, String cedula);
}
