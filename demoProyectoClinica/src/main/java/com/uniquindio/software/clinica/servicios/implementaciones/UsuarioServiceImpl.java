package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Administrador;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IUsuarioDao;
import com.uniquindio.software.clinica.servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(IUsuarioDao usuarioDao) {
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
        return usuarioDao.findById(cedula).orElseThrow(() -> new Exception("No existe el usuario con la cedula: " + cedula));
    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerUsuariosYPacientes(String cedula) {
        return usuarioDao.obtenerUsuariosYPacientes(cedula);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> obtenerMedicos(String cedula) {
        return usuarioDao.obtenerDatosMedico(cedula);
    }

    @Override
    @Transactional(readOnly = true)
    public String obtenerContrasena(String cedula) {
        return usuarioDao.obtenerContrasenaMedPac(cedula);
    }

    @Transactional(readOnly = true)
    public Administrador obtenerAdminPorCorreo(String correo) {
        return usuarioDao.obtenerAdminPorCorreo(correo);
    }

    @Override
    @Transactional(readOnly = true)
    public String obtenerCorreoRP(String cedula) {
        return usuarioDao.obtenerCorreoRP(cedula);
    }

    @Override
    @Transactional
    public void cambiarContrasena(String newPassword, String cedula) {usuarioDao.cambiarContrasena(newPassword, cedula);}

    @Override
    @Transactional()
    public void editarUsuario(String email, String telefono, String url_foto, String cedula) {usuarioDao.editarUsuario(email,telefono,url_foto,cedula);}

    public boolean verificarContrasenaMedPac(String cedula, String contrasenaAVerificar) {
        String storedPasswordHash = usuarioDao.obtenerContrasenaMedPac(cedula);
        return passwordEncoder.matches(contrasenaAVerificar, storedPasswordHash);
    }

    public boolean verificarContrasenaAdmin(String correo, String contrasenaAVerificar) {
        String storedPasswordHash = usuarioDao.obtenerContrasenaAdmin(correo);
        return passwordEncoder.matches(contrasenaAVerificar,storedPasswordHash);
    }
}
