package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.Atencion;
import com.uniquindio.software.clinica.servicios.implementaciones.AtencionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public List<Atencion> listarAtencionesPorMedico(@RequestBody Map<String, Object> cedulaMedico) {
        return atencionService.obtenerAtencionesPorMedico((String)cedulaMedico.get("cedula_medico"));
    }

    @GetMapping("/gestion/atencionesPorPaciente")
    public List<Atencion> listarAtencionesPorPaciente(@RequestBody Map<String, Object> cedulaPaciente) {
        return atencionService.obtenerAtencionesPorPaciente((String)cedulaPaciente.get("cedula_paciente"));
    }
}
