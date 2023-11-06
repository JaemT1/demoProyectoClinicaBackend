package com.uniquindio.software.clinica.servicios;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Usuario;

import java.sql.Date;
import java.util.List;

public interface CitaService {
    List<Cita> listarCitas();
    Cita guardar(Cita cita);
    void eliminar(Cita cita);
    Cita buscarPorId(int id)throws Exception;
    List<Cita> findByFechaCita(Date fecha_cita);
    List<Cita> obtenerCitasProximasPacienteEsp(String cedula_paciente);
    List<Cita> obtenerCitasAnteriores(String cedula_paciente);
    void cambiarEstado(String estado, int id);
}
