package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario,String> {

    @Query("SELECT u,p FROM Usuario u JOIN Paciente p ON u.cedula = p.cedula_usuario")
    List<Object[]> obtenerUsuariosYPacientes();
    @Query("SELECT contrasena FROM Usuario WHERE email = ?1")
    String obtenerContrasena(String correo);


}