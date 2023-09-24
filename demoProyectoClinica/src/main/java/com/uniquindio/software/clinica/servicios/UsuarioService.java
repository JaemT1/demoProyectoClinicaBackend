package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardar(Usuario usuario);
    void eliminar(Usuario usuario);
    Usuario buscarPorCedula(String cedula)throws Exception;
}
