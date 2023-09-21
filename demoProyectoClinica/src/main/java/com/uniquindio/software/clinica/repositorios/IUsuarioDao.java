package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario,String> {

}
