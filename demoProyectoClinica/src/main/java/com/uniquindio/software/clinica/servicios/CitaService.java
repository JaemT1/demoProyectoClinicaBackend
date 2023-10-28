package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Usuario;

import java.util.List;

public interface CitaService {
    List<Cita> listarCitas();
    Cita guardar(Cita cita);
    void eliminar(Cita cita);
    Cita buscarPorId(int id)throws Exception;
}
