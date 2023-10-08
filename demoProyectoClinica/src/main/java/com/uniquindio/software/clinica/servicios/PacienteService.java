package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;

import java.util.List;

public interface PacienteService {
    List<Paciente> listarPacientes();
    Paciente guardar(Paciente paciente);
    void eliminar(Paciente paciente);
    Paciente buscarPorCedula(String cedula)throws Exception;
    void editarPaciente(String alergias, String eps, String cedula);
}

