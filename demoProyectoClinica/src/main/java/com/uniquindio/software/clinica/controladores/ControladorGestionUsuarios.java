package com.uniquindio.software.clinica.controladores;

import com.uniquindio.software.clinica.modelo.Administrador;
import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IEPSDao;
import com.uniquindio.software.clinica.servicios.implementaciones.PacienteServiceImpl;
import com.uniquindio.software.clinica.servicios.implementaciones.UsuarioServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class ControladorGestionUsuarios {
    public String CODIGO_GENERADO_RP = "";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    //--------------------------------Endpoints de los Usuarios--------------------------------
    @GetMapping("/gestion")
    public List<Usuario> listarUsuarios(){
        return usuarioService.listarUsuarios();
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

    @PutMapping("/gestion/editarPaciente")
    public ResponseEntity<String>actualizarUsuario(@RequestBody Map<String, Object> detallesAEditar) throws Exception {
        String email = (String)detallesAEditar.get("email");
        String telefono = (String)detallesAEditar.get("telefono");
        String url_foto = (String)detallesAEditar.get("url_foto");
        String alergias = (String)detallesAEditar.get("alergias");
        String eps = (String)detallesAEditar.get("eps");
        String cedula = (String)detallesAEditar.get("cedula");

        usuarioService.editarUsuario(email, telefono, url_foto, cedula);
        pacienteService.editarPaciente(alergias, eps, cedula);

        return ResponseEntity.ok("Usuario Actualizado");
    }

    @PostMapping ("/gestion/login/paciente")
    public ResponseEntity<Map<String, Object>> verificarLoginPaciente(@RequestBody Map<String, Object> loginData){
        String cedula = (String) loginData.get("cedula");
        String contrasenaAVerificar = (String) loginData.get("password");
        if (usuarioService.verificarContrasenaMedPac(cedula, contrasenaAVerificar)) {
            Map<String, Object> response = new HashMap<>();
            List<Object[]> datosJoin = usuarioService.obtenerUsuariosYPacientes(cedula);
            response.put("message", "Inicio de sesión exitoso");
            response.put("userType", "paciente");
            response.put("userData", datosJoin);
            return ResponseEntity.ok(response);
        } else {
            // Contraseña incorrecta, deniega el acceso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping ("/gestion/login/medico")
    public ResponseEntity<Map<String, Object>> verificarLoginMedico(@RequestBody Map<String, Object> loginData){
        String cedula = (String) loginData.get("codigoMedico");
        String contrasenaAVerificar = (String) loginData.get("passwordMedico");
        if (usuarioService.verificarContrasenaMedPac(cedula, contrasenaAVerificar)) {
            Map<String, Object> response = new HashMap<>();
            List<Object[]> datosJoin = usuarioService.obtenerMedicos(cedula);
            response.put("message", "Inicio de sesión exitoso");
            response.put("userType", "medico");
            response.put("userData", datosJoin);
            return ResponseEntity.ok(response);
        } else {
            // Contraseña incorrecta, deniega el acceso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping ("/gestion/login/admin")
    public ResponseEntity<Map<String, Object>> verificarLoginAdmin(@RequestBody Map<String, Object> loginData){
        String correo = (String) loginData.get("email");
        String contrasenaAVerificar = (String) loginData.get("password_admin");
        Map<String, Object> response = new HashMap<>();
        Administrador admin = usuarioService.obtenerAdminPorCorreo(correo);
        response.put("message", "Inicio de sesión exitoso");
        response.put("userType", "admin");
        response.put("adminData", admin);
        if (usuarioService.verificarContrasenaAdmin(correo, contrasenaAVerificar)) {
            return ResponseEntity.ok(response);
        } else {
            // Contraseña incorrecta, deniega el acceso
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping ("/gestion/login/recuperarContrasena")
    public ResponseEntity<String> obtenerCorreo(@RequestBody Map<String, Object> loginData) throws MessagingException {
        String cedula = (String) loginData.get("cedula");
        String correoBD = usuarioService.obtenerCorreoRP(cedula);

        if (correoBD != null) {
            int codigoVerificacion = generarCodigoVerificacion(6);
            enviarCorreoCV(correoBD, codigoVerificacion);
            return ResponseEntity.ok("Correo Enviado");
        } else {
            // Cedula incorrecta, no existe el afiliado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existe el usuario");
        }
    }

    @PostMapping ("/gestion/login/cambiarContrasena")
    public ResponseEntity<String> cambiarContraseña(@RequestBody Map<String, Object> loginData) throws MessagingException {
        String codigo = (String) loginData.get("verificationCode");
        String contrasena = (String) loginData.get("passwordToSend");
        String cedula = (String) loginData.get("cedula");

        if (codigo.equals(CODIGO_GENERADO_RP)) {
            usuarioService.cambiarContrasena(contrasena, cedula);
            CODIGO_GENERADO_RP = "";
            return ResponseEntity.ok("Contraseña Cambiada con éxito");
        } else {
            // Código digitado incorrecto
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código incorrecto");
        }
    }

    @GetMapping("/gestion/login/IJUP")
    public List<Object[]> listarUsuariosYPacientes(String cedula){
        return usuarioService.obtenerUsuariosYPacientes(cedula);
    }

    public int generarCodigoVerificacion(int cantDigitos) {
        if (cantDigitos < 1) {
            throw new IllegalArgumentException("El número de dígitos debe ser al menos 1.");
        }

        int min = (int) Math.pow(10, cantDigitos - 1); // Mínimo valor posible
        int max = (int) Math.pow(10, cantDigitos) - 1; // Máximo valor posible

        Random random = new Random();
        int codigoVerificacion = random.nextInt(max - min + 1) + min;
        CODIGO_GENERADO_RP = String.valueOf(codigoVerificacion);
        return codigoVerificacion;
    }

    public void enviarCorreoCV(String correoDestino, int codigoVerificacion) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jclinica0@gmail.com");
        message.setTo(correoDestino);
        message.setSubject("Código de Verificación");
        message.setText("Digite este código en el formulario para continuar con su cambio de contraseña: \n\n" + codigoVerificacion
                + "\n\nRespetado afiliado, este correo ha sido generado por un sistema de envío; por favor NO responda al mismo ya que no podrá ser gestionado.");
        mailSender.send(message);
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
