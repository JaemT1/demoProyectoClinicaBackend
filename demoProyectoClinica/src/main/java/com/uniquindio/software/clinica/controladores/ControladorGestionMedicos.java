package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Especializacion;
import com.uniquindio.software.clinica.modelo.Medico;
import com.uniquindio.software.clinica.repositorios.IEspecializacionDao;
import com.uniquindio.software.clinica.servicios.implementaciones.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionMedicos {

    @Autowired
    private MedicoServiceImpl medicoService;

    @GetMapping("/gestion")
    public ResponseEntity<List<Object[]>> listarMedicosYPacientes() {
        List<Object[]> medicosYPacientes = medicoService.obtenerUsuariosYPacientes();
        return ResponseEntity.ok(medicosYPacientes);
    }

    @PostMapping("/crear")
    public ResponseEntity<Medico> crearMedico(@RequestBody Medico nuevoMedico) {
        try {
            Medico medicoCreado = medicoService.guardar(nuevoMedico);
            return ResponseEntity.status(HttpStatus.CREATED).body(medicoCreado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Autowired
    private IEspecializacionDao espService;

    @GetMapping("/especialidades")
    public List<Especializacion> listarEps(){return (List<Especializacion>)espService.findAll();}

    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminarMedico(@RequestBody Medico medico) {
        try {
            medicoService.eliminar(medico);
            return ResponseEntity.ok("Médico eliminado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el médico");
        }
    }

}
