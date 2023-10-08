package com.uniquindio.software.clinica.repositorios;

import com.uniquindio.software.clinica.modelo.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IMedicoDao extends JpaRepository<Medico, String> {

    @Query("SELECT u, m.cedula_usuario, m.especializacion FROM Usuario u JOIN Medico m ON u.cedula = m.cedula_usuario")
    List<Object[]> obtenerMedicosYPacientes();

    @Query("SELECT u, m.cedula_usuario, m.especializacion FROM Usuario u JOIN Medico m ON u.cedula = m.cedula_usuario where m.especializacion = ?1")
    List<Object[]> obtenerMedicosPorEspecializacion(String especializacion);
}
