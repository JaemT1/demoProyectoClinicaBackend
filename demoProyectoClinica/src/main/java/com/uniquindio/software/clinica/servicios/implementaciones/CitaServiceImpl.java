package com.uniquindio.software.clinica.servicios.implementaciones;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.repositorios.ICitaDao;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import com.uniquindio.software.clinica.servicios.CitaService;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private ICitaDao citaDao;
    @Autowired
    private UsuarioServiceImpl usuarioServiceImpl;
    @Autowired
    private CorreoServiceImpl correoService;

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
    public List<Cita> findByFechaCita(Date fecha_cita) {return citaDao.findByFechaCita(fecha_cita);}

    @Override
    @Transactional(readOnly = true)
    public List<Cita> obtenerCitasProximasPacienteEsp(String cedula_paciente) {return citaDao.obtenerCitasProximasPacienteEsp(cedula_paciente);}

    @Override
    @Transactional(readOnly = true)
    public List<Cita> obtenerCitasAnteriores(String cedula_paciente) {return citaDao.obtenerCitasAnteriores(cedula_paciente);}

    @Override
    @Transactional()
    public void cambiarEstado(String estado, int id) {citaDao.cambiarEstado(estado, id);}

    @Override
    @Transactional(readOnly = true)
    public List<Cita> obtenerCitasProximasMedicoEsp(String cedula_medico) {
        return citaDao.obtenerCitasProximasMedicoEsp(cedula_medico);
    }

    public void enviarCorreoAvisoMedico(Cita cita) {
        String nombreUsuario = usuarioServiceImpl.obtenerNombreUsuario(cita.getCedulaPaciente());
        String correoDestino = usuarioServiceImpl.obtenerCorreoRP(cita.getCedulaMedico());
        String contenido = "El afiliado " + nombreUsuario + " con cédula " + cita.getCedulaPaciente() + " ha agendado una cita con usted \n"
                + "Fecha: " + cita.getFechaCita().toString() + "\n"
                + "Hora: " + cita.getHoraCita().toString();
        correoService.enviarEmail("Nueva Cita Agendada", contenido, correoDestino);
    }

    @Scheduled(cron = "0 0 12 * * *") // Se ejecutará a mediodia cada día
    //@Scheduled(fixedRate = 60000)  //Se ejecuta cada minuto a partir de la ejecución anterior (Para realizar pruebas)
    public void verificarCita() {
        List<Cita> citasProximas = citaDao.obtenerCitasProximas();
        LocalDate fechaActual = LocalDate.now();

        for (Cita cita: citasProximas) {
            Date fechaCitaBD = cita.getFechaCita();
            LocalDate fechaCita = LocalDate.parse(fechaCitaBD.toString());
            long diasRestantes = ChronoUnit.DAYS.between(fechaActual, fechaCita);

            if (diasRestantes == 2){
                String nombreUsuario = usuarioServiceImpl.obtenerNombreUsuario(cita.getCedulaPaciente());
                String nombreMedico = usuarioServiceImpl.obtenerNombreUsuario(cita.getCedulaMedico());
                String correoDestino = usuarioServiceImpl.obtenerCorreoRP(cita.getCedulaPaciente());
                String contenido = "Te recordamos que tenemos una cita programada, \nte entregamos nuevamente toda la información que necesitas para asistir: \n"
                                    + "Nombre del Afiliado: " + nombreUsuario + "\n"
                                    + "Profesional a cargo: " + nombreMedico + "\n"
                                    + "Fecha de la cita: " + cita.getFechaCita().toString() + "\n"
                                    + "Hora de la cita: " + cita.getHoraCita().toString() + "\n"
                                    + "Recuerda llegar 20 minutos antes de la hora programada.";
                correoService.enviarEmail("Clínica San Gabriel Te Recuerda Tu Cita", contenido, correoDestino);
            }
        }
    }
}
