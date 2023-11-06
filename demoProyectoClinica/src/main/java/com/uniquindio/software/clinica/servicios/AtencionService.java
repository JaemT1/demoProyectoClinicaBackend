package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Atencion;

import java.util.List;

public interface AtencionService {

    List<Atencion> listarAtenciones();
    Atencion guardar(Atencion atencion);
    void eliminar(Atencion atencion);
    Atencion buscarPorId(int id)throws Exception;
    List<Atencion> obtenerAtencionesPorMedico(String cedulaMedico);
    List<Atencion> obtenerAtencionesPorPaciente(String cedulaPaciente);

}
