package com.uniquindio.software.clinica.servicios.implementaciones;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Iterator;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.repositorios.ICitaDao;
import com.uniquindio.software.clinica.repositorios.IPacienteDao;
import com.uniquindio.software.clinica.servicios.CitaServicio;

@Service
public class CitaServiceImpl implements CitaServicio {


    @Autowired
    public ICitaDao citaRepo;
    @Autowired
    public IPacienteDao pacienteDao;
    @Override
    public ArrayList<String> listarCitasProximas(LocalDate fecha, Integer diasPrevios) {
        ArrayList<String> listaPacientes = new ArrayList<String>();

        Iterable<Cita> citas = citaRepo.findAll();
        Iterator<Cita> citaIterator = citas.iterator();
        Cita cita;
        while( citaIterator.hasNext()) {
            cita =  citaIterator.next();
            if ( cita.getFechaCita().after(Date.from (fecha.minusDays(diasPrevios).atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
                listaPacientes.add(cita.getCedulaPaciente());
            }
        }
        return listaPacientes;
    }

    @Override
    public void enviarCorreosRecordatorio() {
        
        this.listarCitasProximas(LocalDate.now(), 2).forEach(
            e -> {

                //Flata por implementar
            }
        );

    }   

}
