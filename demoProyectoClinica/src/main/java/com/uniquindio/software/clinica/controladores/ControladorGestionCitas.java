package com.uniquindio.software.clinica.controladores;


import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.servicios.implementaciones.CitaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionCitas {

    @Autowired
    private CitaServiceImpl citaService;

    @PostMapping("/gestion")
    public Cita guardarCita(@RequestBody Map<String, Object> datosCita){

        long currentTimeMillis = System.currentTimeMillis();
        Date fechaCreacion = new Date(currentTimeMillis);
        Time horaCreacion = new Time(currentTimeMillis);

        Date fechaCita = Date.valueOf((String)datosCita.get("fecha_cita"));
        Time horaCita = Time.valueOf((String)datosCita.get("hora_cita"));

        String cedula_medico = (String)datosCita.get("cedula_medico");
        String cedula_paciente = (String)datosCita.get("cedula_paciente");
        String estado = (String)datosCita.get("estado");
        String motivo = (String)datosCita.get("motivo");

        Cita cita = new Cita(fechaCreacion, horaCreacion, fechaCita, horaCita, cedula_medico, cedula_paciente, estado, motivo);
        return citaService.guardar(cita);
    }

    @GetMapping("/gestion")
    public List<Cita> listarUsuarios(){
        return citaService.listarCitas();
    }


}
