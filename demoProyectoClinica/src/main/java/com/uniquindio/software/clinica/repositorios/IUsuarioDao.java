package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Administrador;
import com.uniquindio.software.clinica.modelo.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUsuarioDao extends CrudRepository<Usuario,String> {

    @Query("SELECT u,p FROM Usuario u JOIN Paciente p ON u.cedula = p.cedula_usuario WHERE u.cedula = ?1")
    List<Object[]> obtenerUsuariosYPacientes(String cedula);
    @Query("SELECT contrasena FROM Usuario WHERE cedula = ?1")
    String obtenerContrasenaMedPac(String cedula);
    @Query("SELECT contrasena FROM Administrador WHERE email = ?1")
    String obtenerContrasenaAdmin(String email);
    @Query("SELECT a FROM Administrador a WHERE a.email = ?1")
    Administrador obtenerAdminPorCorreo(String correo);
    @Query("SELECT u,m FROM Usuario u JOIN Medico m ON u.cedula = m.cedula_usuario WHERE u.cedula = ?1")
    List<Object[]> obtenerDatosMedico(String cedula);
    @Query("SELECT u.email FROM Usuario u  WHERE u.cedula = ?1")
    String obtenerCorreoRP(String cedula);
    @Modifying
    @Query("UPDATE Usuario u SET u.contrasena = ?1 WHERE u.cedula = ?2")
    void cambiarContrasena(String newPassword, String cedula);
    @Modifying
    @Query("UPDATE Usuario u SET u.email = ?1, u.telefono = ?2, u.url_foto = ?3  WHERE u.cedula = ?4")
    void editarUsuario(String email, String telefono, String url_foto, String cedula);
}