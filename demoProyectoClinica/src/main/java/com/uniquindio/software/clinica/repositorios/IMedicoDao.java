package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicoDao extends JpaRepository<Medico, String> {

    @Query("SELECT u, m.cedula_usuario, m.especializacion FROM Usuario u JOIN Medico m ON u.cedula = m.cedula_usuario")
    List<Object[]> obtenerMedicosYPacientes();
    
    
}
