package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Medico;

import java.util.List;

public interface MedicoService {
    List<Medico> listarMedicos();
    Medico guardar(Medico medico);
    void eliminar(Medico medico);
    Medico buscarPorCedula(String cedula) throws Exception;
    List<Object[]> obtenerUsuariosYPacientes(String cedula);
    List<Object[]> obtenerMedicosPorEspecializacion(String especializacion);
}
