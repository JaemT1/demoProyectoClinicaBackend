package com.uniquindio.software.clinica.servicios.implementaciones;

import com.uniquindio.software.clinica.modelo.Paciente;
import com.uniquindio.software.clinica.repositorios.IPacienteDao;
import com.uniquindio.software.clinica.servicios.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    private IPacienteDao pacienteDao;
    @Override
    @Transactional
    public Paciente guardar(Paciente paciente) {
        return pacienteDao.save(paciente);
    }
    @Override
    @Transactional(readOnly = true)
    public ArrayList<Paciente> listarPacientes() {
        return (ArrayList<Paciente>) pacienteDao.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Paciente buscarPorCedula(String cedula) throws Exception {
        return pacienteDao.findById(cedula).orElseThrow(()->new Exception("No existe el usuario con la cedula: " + cedula));
    }
    @Override
    @Transactional()
    public void editarPaciente(String alergias, String eps, String cedula) {pacienteDao.editarPaciente(alergias, eps, cedula);}
    @Override
    @Transactional
    public void eliminar(Paciente paciente) {
        pacienteDao.delete(paciente);
    }
}
