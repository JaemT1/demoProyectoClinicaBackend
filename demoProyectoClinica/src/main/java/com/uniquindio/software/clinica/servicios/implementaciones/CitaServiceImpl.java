package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.repositorios.ICitaDao;
import com.uniquindio.software.clinica.servicios.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private ICitaDao citaDao;
    @Override
    @Transactional(readOnly = true)
    public List<Cita> listarCitas() {return (ArrayList<Cita>) citaDao.findAll();}
    @Override
    @Transactional
    public Cita guardar(Cita cita) {return citaDao.save(cita);}
    @Override
    @Transactional
    public void eliminar(Cita cita) {citaDao.delete(cita);}
    @Override
    @Transactional(readOnly = true)
    public Cita buscarPorId(int id) throws Exception {
        return citaDao.findById(id).orElseThrow(() -> new Exception("No existe la cita con el id: " + id));
    }
}
