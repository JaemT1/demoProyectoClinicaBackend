package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.MedicoHasJornada;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedicoHasJornada extends CrudRepository<MedicoHasJornada, Integer> {
}
