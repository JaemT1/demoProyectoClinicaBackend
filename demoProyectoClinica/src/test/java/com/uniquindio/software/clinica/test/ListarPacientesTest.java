package com.uniquindio.software.clinica.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.EPS;
import com.uniquindio.software.clinica.modelo.Estado;
import com.uniquindio.software.clinica.modelo.Medico;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.repositorios.ICitaDao;
import com.uniquindio.software.clinica.servicios.CitaServicio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ListarPacientesTest {


    @Autowired
    public CitaServicio citaServicio;
    @Autowired
    public ICitaDao citaDao;
    @Test
    @Sql("classpath:dataset.sql")
    public void enviarCorreoCitasProximas() {
        citaServicio.enviarCorreosRecordatorio();
    }
}
