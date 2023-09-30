package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IEPSDao;
import com.uniquindio.software.clinica.servicios.implementaciones.PacienteServiceImpl;
import com.uniquindio.software.clinica.servicios.implementaciones.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionUsuarios {
    //--------------------------------Endpoints de los Usuarios--------------------------------

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @GetMapping("/gestion")
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/gestion/login/IJUP")
    public List<Object[]> listarUsuariosYPacientes(){
        return usuarioService.obtenerUsuariosYPacientes();
    }

    @PostMapping ("/gestion/login/medpac")
    public ResponseEntity<String> verificarLoginMedPac(@RequestBody Map<String, Object> loginData){
        String cedula = (String) loginData.get("cedula");
        String contrasenaAVerificar = (String) loginData.get("password");
        if (usuarioService.verificarContrasenaMedPac(cedula, contrasenaAVerificar)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            // Contraseña incorrecta, deniega el acceso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping ("/gestion/login/admin")
    public ResponseEntity<String> verificarLoginAdmin(@RequestBody Map<String, Object> loginData){
        String correo = (String) loginData.get("email");
        String contrasenaAVerificar = (String) loginData.get("password_admin");
        if (usuarioService.verificarContrasenaAdmin(correo, contrasenaAVerificar)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            // Contraseña incorrecta, deniega el acceso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @PostMapping("/gestion")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){return usuarioService.guardar(usuario);}

    @DeleteMapping("/gestion")
    public void borrarUsuario(@RequestBody Usuario usuario){ usuarioService.eliminar(usuario);}

    @GetMapping("/gestion/{cedula}")
    public ResponseEntity<Usuario>obtenerUsuarioPorCedula(@PathVariable String cedula) throws Exception {
        Usuario usuario = usuarioService.buscarPorCedula(cedula);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/gestion/{cedula}")
    public ResponseEntity<Usuario>actualizarUsuario(@PathVariable String cedula, @RequestBody Usuario detallesUsuario) throws Exception {
        Usuario usuario = usuarioService.buscarPorCedula(cedula);

        usuario.setCedula(detallesUsuario.getCedula());
        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setEmail(detallesUsuario.getEmail());
        usuario.setContrasena(detallesUsuario.getContrasena());
        usuario.setTelefono(detallesUsuario.getTelefono());
        usuario.setCiudad(detallesUsuario.getCiudad());

        Usuario usuarioActualizado = usuarioService.guardar(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }
    //----------------------------------------------------------------------------------------------

    //--------------------------------Endpoints de los Pacientes--------------------------------
    @Autowired
    private PacienteServiceImpl pacienteService;

    @GetMapping("/gestion/pacientes")
    public List<Paciente> listarPacientes(){
        return pacienteService.listarPacientes();
    }

    @PostMapping("/gestion/pacientes")
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);}

    @DeleteMapping("/gestion/pacientes")
    public void borrarPaciente(@RequestBody Paciente paciente){ pacienteService.eliminar(paciente);}

    @GetMapping("/gestion/pacientes/{cedula}")
    public ResponseEntity<Paciente> obtenerPacientePorCedula(@PathVariable String cedula) throws Exception {
        Paciente paciente = pacienteService.buscarPorCedula(cedula);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping("/gestion/pacientes/{cedula}")
    public ResponseEntity<Paciente>actualizarPaciente(@PathVariable String cedula, @RequestBody Paciente detallesPaciente) throws Exception {
        Paciente paciente = pacienteService.buscarPorCedula(cedula);
        paciente.setFecha_nacimiento(detallesPaciente.getFecha_nacimiento());
        paciente.setAlergias(detallesPaciente.getAlergias());
        paciente.setEps(detallesPaciente.getEps());
        paciente.setTipo_sangre(detallesPaciente.getTipo_sangre());
        Paciente pacienteActualizado = pacienteService.guardar(paciente);
        return ResponseEntity.ok(pacienteActualizado);
    }
    //----------------------------------------------------------------------------------------------

    //--------------------------------Endpoints de las EPS------------------------------------------
    @Autowired
    private IEPSDao epsService;

    @PostMapping("/gestion/eps")
    public EPS guardarEps(@RequestBody EPS eps){return epsService.save(eps);}

    @GetMapping("/gestion/eps")
    public List<EPS> listarEps(){return (List<EPS>)epsService.findAll();}
    //----------------------------------------------------------------------------------------------
}
