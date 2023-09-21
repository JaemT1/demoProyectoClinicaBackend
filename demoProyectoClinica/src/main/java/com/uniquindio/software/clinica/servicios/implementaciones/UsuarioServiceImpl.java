package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IUsuarioDao;
import com.uniquindio.software.clinica.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private IUsuarioDao usuarioDao;
    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
    @Override
    @Transactional(readOnly = true)
    public ArrayList<Usuario> listarUsuarios() {
        return (ArrayList<Usuario>) usuarioDao.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorCedula(String cedula) throws Exception {
        return usuarioDao.findById(cedula).orElseThrow(()->new Exception("No existe el usuario con la cedula: " + cedula));
    }
    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
}
