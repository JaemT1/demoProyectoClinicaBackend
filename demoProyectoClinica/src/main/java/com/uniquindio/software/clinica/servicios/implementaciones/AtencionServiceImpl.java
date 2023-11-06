package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Atencion;
import com.uniquindio.software.clinica.modelo.Cita;
import com.uniquindio.software.clinica.repositorios.IAtencionDao;
import com.uniquindio.software.clinica.servicios.AtencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtencionServiceImpl implements AtencionService {

    @Autowired
    private IAtencionDao atencionDao;

    @Override
    @Transactional(readOnly = true)
    public List<Atencion> listarAtenciones() {
        return (List<Atencion>)atencionDao.findAll();
    }

    @Override
    @Transactional
    public Atencion guardar(Atencion atencion) {return atencionDao.save(atencion);}

    @Override
    @Transactional
    public void eliminar(Atencion atencion) {atencionDao.delete(atencion);}

    @Override
    @Transactional(readOnly = true)
    public Atencion buscarPorId(int id) throws Exception {return atencionDao.findById(id).orElseThrow(() -> new Exception("No existe la atención con el id: " + id));}

    @Override
    @Transactional(readOnly = true)
    public List<Atencion> obtenerAtencionesPorMedico(String cedulaMedico) {return atencionDao.obtenerAtencionesPorMedico(cedulaMedico);}

    @Override
    @Transactional(readOnly = true)
    public List<Atencion> obtenerAtencionesPorPaciente(String cedulaPaciente) {return atencionDao.obtenerAtencionesPorPaciente(cedulaPaciente);}
}
