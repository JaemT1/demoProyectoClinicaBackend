package com.uniquindio.software.clinica.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    public void enviarCorreoCitasProximas() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unidadClinica");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        
        

        //Usuario usuario1 = new Usuario("1001239815", "Juan Ortega", "JuanOrtega2001", "juanO.gmail.com", "3112335678", "Armenia", null);
        //Paciente paciente1 = new Paciente();

        //Usuario usuario2 = new Usuario("1023467156", "Juan Lopez", "JuanLopez1983", "juanO.gmail.com", "3024512354", "Armenia", null);
        //Medico medico1 = new Medico("1023467156", "Medico general");
        
        //paciente1.setCedula_usuario("1001239815");
        //paciente1.setFecha_nacimiento( new Date(2001, 10, 23) );

        //Cita cita1 = new Cita(100234, new Date(2023, 10, 24), new Date(2023, 10, 27), "1023467156", "1001239815", Estado.programada, "");
        //em.persist(paciente1);
        //em.persist(medico1);
        //em.persist(cita1);
//        EPS foo = new EPS();
  //      foo.setNombre("SURA");
    //    em.persist(foo);
      //  em.getTransaction().commit();

        //em.close();
        //emf.close();

        //List<String> pacientes = citaServicio.listarCitasProximas(new Date(2023, 10, 28));
        //Iterable<Cita> pacientes = citaDao.findAll();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.m");
        List<String> cita = citaServicio.listarCitasProximas(LocalDate.of(2023, 10, 28), 2);
        assertTrue(cita.get(0).equals("1001239815"), "wrong");
        //assertEquals(pacientes.iterator().next().getFechaCita(), sdf.format( new Date(123, 9, 27, 19, 0)) );

        //Assert(pacientes.get() == 1);
        //citaServicio.listarCitasProximas(new Date((new java.util.Date()).getTime())).
        //forEach(e->{System.out.println(e);});


    }
}
