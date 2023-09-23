package com.uniquindio.software.clinica.controladores;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.IEPSDao;
import com.uniquindio.software.clinica.servicios.implementaciones.PacienteServiceImpl;
import com.uniquindio.software.clinica.servicios.implementaciones.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.File;
import java.util.List;
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
    public Paciente guardarPaciente(@RequestBody Paciente paciente){return pacienteService.guardar(paciente);}

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


        paciente.setFechaNacimiento(detallesPaciente.getFechaNacimiento());
        paciente.setAlergias(detallesPaciente.getAlergias());
        paciente.setEps(detallesPaciente.getEps());
        paciente.setTipoSangre(detallesPaciente.getTipoSangre());

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
    //--------------------------------Subir y obtener imagen de Cloudinary--------------------------------
    public void includesForUploadFiles(File foto, String cedula) throws Exception {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dkm9g0zpt",
                "api_key", "654495213436479",
                "api_secret", "PIJO3ukm6rEsZFGjOIK7gcVDV-g"));
        cloudinary.uploader().upload(foto,
                ObjectUtils.asMap("public_id", cedula));

        //Ejemplos
        ApiResponse result = cloudinary.api().resource("r34", ObjectUtils.emptyMap());
        ApiResponse result2 = cloudinary.api().resource("la roca chiquito", ObjectUtils.emptyMap());
        System.out.println(result.get("url"));
        System.out.println(result2.get("url"));
    }
    //----------------------------------------------------------------------------------------------
}
