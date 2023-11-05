package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.Atencion;
import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.servicios.implementaciones.AtencionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
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

}
