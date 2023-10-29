package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.MedicoHasJornada;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoHasJornada extends CrudRepository<MedicoHasJornada, Integer> {

    @Query("SELECT id_jornada FROM MedicoHasJornada WHERE cedula_medico = ?1")
    int obtenerIdJornada(String cedula);

}
