package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Administrador;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IUsuarioDao;
import com.uniquindio.software.clinica.servicios.UsuarioService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final PasswordEncoder passwordEncoder;

    public String CODIGO_GENERADO_RP = "";

    public UsuarioServiceImpl(IUsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Autowired
    private IUsuarioDao usuarioDao;

    @Autowired
    private JavaMailSender mailSender;

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
    public String obtenerNombreUsuario(String cedula) {return usuarioDao.obtenerNombreUsuario(cedula);}

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

    public int generarCodigoVerificacion(int cantDigitos) {
        if (cantDigitos < 1) {
            throw new IllegalArgumentException("El número de dígitos debe ser al menos 1.");
        }

        int min = (int) Math.pow(10, cantDigitos - 1); // Mínimo valor posible
        int max = (int) Math.pow(10, cantDigitos) - 1; // Máximo valor posible

        Random random = new Random();
        int codigoVerificacion = random.nextInt(max - min + 1) + min;
        CODIGO_GENERADO_RP = String.valueOf(codigoVerificacion);
        return codigoVerificacion;
    }

    public void enviarCorreoCV(String correoDestino, int codigoVerificacion) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jclinica0@gmail.com");
        message.setTo(correoDestino);
        message.setSubject("Código de Verificación");
        message.setText("Digite este código en el formulario para continuar con su cambio de contraseña: \n\n" + codigoVerificacion
                + "\n\nRespetado afiliado, este correo ha sido generado por un sistema de envío; por favor NO responda al mismo ya que no podrá ser gestionado.");
        mailSender.send(message);
    }
}
