package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.EPS;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEPSDao extends CrudRepository<EPS,String> {
}
