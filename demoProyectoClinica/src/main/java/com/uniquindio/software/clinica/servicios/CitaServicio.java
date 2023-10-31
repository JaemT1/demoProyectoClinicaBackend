package com.uniquindio.software.clinica.servicios;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import org.springframework.cglib.core.Local;

import com.uniquindio.software.clinica.modelo.Cita;

public interface CitaServicio {
    List<Cita> listarCitasProximas(Date fecha, Integer diasPrevios);
    void enviarCorreosRecordatorio();
}
