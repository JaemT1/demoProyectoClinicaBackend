package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IUsuarioDao;
import com.uniquindio.software.clinica.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(IUsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

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
    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerUsuariosYPacientes() {
        return usuarioDao.obtenerUsuariosYPacientes();
    }
    @Override
    @Transactional(readOnly = true)
    public String obtenerContrasena(String email) {
        return usuarioDao.obtenerContrasena(email);
    }

    public boolean verificarContrasena(String email, String contrasenaAVerificar) {
        String storedPasswordHash = usuarioDao.obtenerContrasena(email);
        // Contraseña válida, permite el acceso
        // Contraseña incorrecta, deniega el acceso
        return passwordEncoder.matches(contrasenaAVerificar, storedPasswordHash);
    }

}
