package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.Atencion;
import com.uniquindio.software.clinica.servicios.implementaciones.AtencionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atenciones")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionAtenciones {

    @Autowired
    private AtencionServiceImpl atencionService;

    @PostMapping("/gestion")
    public Atencion guardarCita(@RequestBody Atencion atencion) {return atencionService.guardar(atencion);}

    @GetMapping("/gestion")
    public List<Atencion> listarAtenciones() {return atencionService.listarAtenciones();}

    @GetMapping("/gestion/atencionesPorMedico")
    public List<Atencion> listarAtencionesPorMedico(@RequestParam String cedula_medico) {
        return atencionService.obtenerAtencionesPorMedico(cedula_medico);
    }

    @GetMapping("/gestion/atencionesPorPaciente")
    public List<Atencion> listarAtencionesPorPaciente(@RequestParam String cedula_paciente) {
        return atencionService.obtenerAtencionesPorPaciente(cedula_paciente);
    }
}
