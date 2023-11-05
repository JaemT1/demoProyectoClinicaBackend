package com.uniquindio.software.clinica.modelo;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Atencion {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAtencion;
    private int idCita;
    private String sintomas;
    private String diagnostico;
    private String notasMedicas;
    private String tratamiento;

    public Atencion() {
    }

    public Atencion(int idCita, String sintomas, String diagnostico, String notasMedicas, String tratamiento) {
        this.idCita = idCita;
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.notasMedicas = notasMedicas;
        this.tratamiento = tratamiento;
    }
}
