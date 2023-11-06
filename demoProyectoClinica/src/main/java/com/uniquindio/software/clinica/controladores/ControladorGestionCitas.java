package com.uniquindio.software.clinica.controladores;


import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.servicios.implementaciones.CitaServiceImpl;
import com.uniquindio.software.clinica.servicios.implementaciones.MedicoHasJornadaImpl;
import com.uniquindio.software.clinica.servicios.implementaciones.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionCitas {

    @Autowired
    private CitaServiceImpl citaService;
    @Autowired
    private MedicoHasJornadaImpl medicoHasJornadaService;

    @PostMapping("/gestion")
    public Cita guardarCita(@RequestBody Map<String, Object> datosCita) {

        long currentTimeMillis = System.currentTimeMillis();
        Date fechaCreacion = new Date(currentTimeMillis);
        Time horaCreacion = new Time(currentTimeMillis);

        Date fechaCita = Date.valueOf((String) datosCita.get("fecha_cita"));
        Time horaCita = Time.valueOf(datosCita.get("hora_cita") + ":00");

        String cedula_medico = (String) datosCita.get("cedula_medico");
        String cedula_paciente = (String) datosCita.get("cedula_paciente");
        String estado = (String) datosCita.get("estado");
        String motivo = (String) datosCita.get("motivo");

        Cita cita = new Cita(fechaCreacion, horaCreacion, fechaCita, horaCita, cedula_medico, cedula_paciente, estado, motivo);
        citaService.enviarCorreoAvisoMedico(cita);
        return citaService.guardar(cita);
    }

    @PostMapping("/gestion/horas_disponibles")
    public List<String> disponibilidadMedico(@RequestBody Map<String, Object> fechaYCedula) throws Exception {

        String cedula_medico = (String)fechaYCedula.get("cedula_medico");
        Date fechaCita = Date.valueOf((String)fechaYCedula.get("fecha_cita"));

        ArrayList<String> disponibilidad = new ArrayList<>(Arrays.asList(medicoHasJornadaService.asignacionHorasMedico(medicoHasJornadaService.obtenerIdJornada(cedula_medico))));
        List<Cita> citasConFechaBuscada = citaService.findByFechaCita(fechaCita);

        for (Cita cita : citasConFechaBuscada) {
            String horaCita = new SimpleDateFormat("HH:mm").format(cita.getHoraCita());
            disponibilidad.remove(horaCita);
        }

        return disponibilidad;
    }

    @GetMapping("/gestion")
    public List<Cita> listarCitas() {return citaService.listarCitas();}

    @GetMapping("/gestion/citasProximas")
    public List<Cita> obtenerCitasProximas(@RequestBody Map<String, Object> cedula) {return citaService.obtenerCitasProximasPacienteEsp((String)cedula.get("cedula")); }

    @GetMapping("/gestion/citasAnteriores")
    public List<Cita> obtenerCitasAnteriores(@RequestBody Map<String, Object> cedula) {return citaService.obtenerCitasAnteriores((String)cedula.get("cedula"));}

    @PostMapping("/gestion/cambiarEstadoCita")
    public void cambiarEstadoCita(@RequestBody Map<String, Object> datos){
        int idCita = (Integer)datos.get("id_cita");
        citaService.cambiarEstado("Completada", idCita);
    }
}
