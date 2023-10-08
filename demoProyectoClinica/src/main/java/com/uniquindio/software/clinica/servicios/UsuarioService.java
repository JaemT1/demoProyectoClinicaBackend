package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardar(Usuario usuario);
    void eliminar(Usuario usuario);
    Usuario buscarPorCedula(String cedula)throws Exception;
    List<Object[]> obtenerUsuariosYPacientes(String cedula);
    String obtenerContrasena(String cedula);
    List<Object[]> obtenerMedicos(String cedula);
    String obtenerCorreoRP(String cedula);
    void cambiarContrasena(String newPassword, String cedula);
    void editarUsuario(String email, String telefono, String url_foto, String cedula);
}
