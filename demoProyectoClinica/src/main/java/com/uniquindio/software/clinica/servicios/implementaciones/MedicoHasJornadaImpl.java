package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.MedicoHasJornada;
import com.uniquindio.software.clinica.repositorios.IMedicoHasJornada;
import com.uniquindio.software.clinica.servicios.MedicoHasJornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoHasJornadaImpl implements MedicoHasJornadaService {

    public String[] HORAS7A13 = {"07:00", "07:20", "07:40", "08:00", "08:20", "08:40", "09:00", "09:20", "09:40",
            "10:00", "10:20", "10:40", "11:00", "11:20", "11:40", "12:00", "12:20", "12:40"};

    public String[] HORAS13A20 = {"13:00", "13:20", "13:40", "14:00", "14:20", "14:40", "15:00", "15:20", "15:40",
            "16:00", "16:20", "16:40", "17:00", "17:20", "17:40", "18:00", "18:20", "18:40", "19:00", "19:20", "19:40"};

    public String[] HORAS20A24 = {"20:00", "20:20", "20:40", "21:00", "21:20", "21:40", "22:00", "22:20", "22:40", "23:00",
            "23:20", "23:40"};

    @Autowired
    private IMedicoHasJornada medicoHasJornadaDao;

    @Override
    @Transactional(readOnly = true)
    public int obtenerIdJornada(String cedula_medico) {
        return medicoHasJornadaDao.obtenerIdJornada(cedula_medico);
    }

    public String[] asignacionHorasMedico(int idJornada) throws Exception {
        if (idJornada == 1) {
            return HORAS7A13;
        }
        if (idJornada == 2) {
            return HORAS13A20;
        }
        if (idJornada == 3) {
            return HORAS20A24;
        }
        return null;
    }
}

