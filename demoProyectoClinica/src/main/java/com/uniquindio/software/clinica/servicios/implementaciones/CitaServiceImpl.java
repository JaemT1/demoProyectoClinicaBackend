package com.uniquindio.software.clinica.servicios.implementaciones;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.util.Iterator;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.repositorios.ICitaDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.ICitaDao;
import com.uniquindio.software.clinica.repositorios.IPacienteDao;
import com.uniquindio.software.clinica.servicios.CitaServicio;

@Service
public class CitaServiceImpl implements CitaServicio {


    @Autowired
    public ICitaDao citaRepo;
    @Autowired
    public IPacienteDao pacienteDao;
    @Autowired
    public CorreoServicio correoServicio;
    @Autowired
    public UsuarioServiceImpl usuarioService;

    BufferedWriter bufferedWriter;
    FileWriter fileWriter;

    @Override
    public ArrayList<Cita> listarCitasProximas( Date fecha, Integer diasPrevios) {
        ArrayList<Cita> listaCitas = new ArrayList<Cita>();

        Iterable<Cita> citas = citaRepo.findAll();
        Iterator<Cita> citaIterator = citas.iterator();
        Cita cita;
        while( citaIterator.hasNext()) {
            cita =  citaIterator.next();
            if ( cita.getFechaCita().getTime() - fecha.getTime()  < diasPrevios * 24 * 3600 * 1000L ) {
                listaCitas.add(cita);
            }
        }
        return listaCitas;
    }

    @Override
    @Scheduled(cron = "0 0 * * * *")
    public void enviarCorreosRecordatorio() {
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.listarCitasProximas(new Date(System.currentTimeMillis()), 3).forEach(
            cita -> {
                 try {
                    
                    Usuario usuario = usuarioService.buscarPorCedula( cita.getCedulaPaciente() );
                    correoServicio.enviarEmail("Recordatorio cita", 
                                               String.format(
                                                            "Reciba una cordial saludo %s.<br><br> la presente es para recordale"+
                                                            " su proxima cita el dia <strong>%s</strong>, a la(s) <strong>%s</strong>, recuerde que debe"+
                                                            " llegar 20 minutos antes y traer su orden. <br><br>Que tenga buen dia.", 
                                                            usuario.getNombre(), 
                                                            simpleDateFormat.format( cita.getFechaCita() ), cita.getHoraCita() ), 
                                               usuario.getEmail());
        
        } catch (Exception excp) {
            excp.printStackTrace();
        }
            }
        );

    }   


    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ICitaDao citaDao;

    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;


    @Transactional(readOnly = true)
    public List<Cita> listarCitas() {return (ArrayList<Cita>) citaDao.findAll();}
    
    @Transactional
    public Cita guardar(Cita cita) {return citaDao.save(cita);}
    
    @Transactional
    public void eliminar(Cita cita) {citaDao.delete(cita);}
    
    @Transactional(readOnly = true)

    public Cita buscarPorId(int id) throws Exception {return citaDao.findById(id).orElseThrow(() -> new Exception("No existe la cita con el id: " + id));}


    

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
