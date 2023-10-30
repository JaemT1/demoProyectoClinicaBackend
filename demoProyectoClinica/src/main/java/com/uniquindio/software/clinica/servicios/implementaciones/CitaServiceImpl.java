package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.repositorios.ICitaDao;
import com.uniquindio.software.clinica.servicios.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ICitaDao citaDao;
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;

    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarCitas() {return (ArrayList<Cita>) citaDao.findAll();}
    @Override
    @Transactional
    public Cita guardar(Cita cita) {return citaDao.save(cita);}
    @Override
    @Transactional
    public void eliminar(Cita cita) {citaDao.delete(cita);}
    @Override
    @Transactional(readOnly = true)
    public Cita buscarPorId(int id) throws Exception {return citaDao.findById(id).orElseThrow(() -> new Exception("No existe la cita con el id: " + id));}
    @Override
    @Transactional(readOnly = true)
    public List<Cita> findByFechaCita(Date fecha_cita) {
        return citaDao.findByFechaCita(fecha_cita);
    }
    public void enviarCorreoAvisoMedico(Cita cita) {
        String nombreUsuario = usuarioServiceImpl.obtenerNombreUsuario(cita.getCedulaPaciente());
        String correoDestino = usuarioServiceImpl.obtenerCorreoRP(cita.getCedulaMedico());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jclinica0@gmail.com");
        message.setTo(correoDestino);
        message.setSubject("Nueva Cita Agendada");
        message.setText("El afiliado " + nombreUsuario + " con cédula " + cita.getCedulaPaciente() + " ha agendado una cita con usted \n"
                        + "Fecha: " + cita.getFechaCita().toString() + "\n"
                        + "Hora: " + cita.getHoraCita().toString());
        mailSender.send(message);
    }
}
