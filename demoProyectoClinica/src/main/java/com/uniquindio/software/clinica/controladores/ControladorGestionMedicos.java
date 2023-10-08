package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Especializacion;
import com.uniquindio.software.clinica.modelo.Medico;
import com.uniquindio.software.clinica.modelo.MedicoHasJornada;
import com.uniquindio.software.clinica.repositorios.IEspecializacionDao;
import com.uniquindio.software.clinica.repositorios.IMedicoHasJornada;
import com.uniquindio.software.clinica.servicios.implementaciones.MedicoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionMedicos {

    @Autowired
    private MedicoServiceImpl medicoService;

    @Autowired
    private IMedicoHasJornada medicoJornadaRepository;

    @GetMapping("/gestion")
    public ResponseEntity<List<Object[]>> listarMedicosYPacientes() {
        List<Object[]> medicosYPacientes = medicoService.obtenerUsuariosYPacientes();
        return ResponseEntity.ok(medicosYPacientes);
    }

    @GetMapping("/gestion/medicosPorEspecializacion")
    public ResponseEntity<List<Object[]>> listarMedicosPorEspecializacion(@RequestBody Map<String, Object> especializacionABuscar) {
        String especializacion = (String) especializacionABuscar.get("especializacion");
        List<Object[]> medicosPorEspecializacion = medicoService.obtenerMedicosPorEspecializacion(especializacion);
        return ResponseEntity.ok(medicosPorEspecializacion);
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

    @PostMapping("/horario")
    public ResponseEntity<String> asignarHorario(@RequestBody Map<String, Object> scheduleData) {
        try {
            int id_jornada = (Integer)scheduleData.get("id_jornada");
            String cedula_medico = (String)scheduleData.get("cedula_medico");
            MedicoHasJornada jornada_medico = new MedicoHasJornada(id_jornada, cedula_medico);
            medicoJornadaRepository.save(jornada_medico);
            return ResponseEntity.status(HttpStatus.CREATED).body("Horario Añadido");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error añadiendo el horario");
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
