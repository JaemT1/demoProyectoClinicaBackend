package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Especializacion;
import com.uniquindio.software.clinica.modelo.Medico;
import com.uniquindio.software.clinica.repositorios.IEspecializacionDao;
import com.uniquindio.software.clinica.repositorios.IMedicoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicoServiceImpl {
    @Autowired
    private IMedicoDao medicoDao;


    @Transactional
    public Medico guardar(Medico nuevoMedico) {
        try {
            return medicoDao.save(nuevoMedico);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el médico: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public List<Medico> listarMedicos() {
        return medicoDao.findAll();
    }

    @Transactional(readOnly = true)
    public Medico buscarPorCedula(String cedula) throws Exception {
        return medicoDao.findById(cedula).orElseThrow(() -> new Exception("No existe el médico con la cedula: " + cedula));
    }

    @Transactional
    public void eliminar(Medico medico) {
        medicoDao.delete(medico);
    }

    @Transactional(readOnly = true)
    public List<Object[]> obtenerUsuariosYPacientes() {
        return medicoDao.obtenerMedicosYPacientes();
    }

    @Transactional(readOnly = true)
    public List<Object[]> obtenerMedicosPorEspecializacion(String especializacion) {
        return medicoDao.obtenerMedicosPorEspecializacion(especializacion);
    }
}
