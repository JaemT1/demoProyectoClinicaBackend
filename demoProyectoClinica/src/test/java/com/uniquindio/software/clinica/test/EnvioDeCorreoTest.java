package com.uniquindio.software.clinica.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.uniquindio.software.clinica.modelo.Usuario;
import com.uniquindio.software.clinica.servicios.implementaciones.CorreoServicio;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class EnvioDeCorreoTest {


    @Autowired
    public CorreoServicio emailServicio;
    @Test
    public void enviarCorreo() {
        Usuario usr = new Usuario();
        usr.setEmail("anobish.correau@uqvirtual.edu.co");
        emailServicio.enviarEmail("Se ha realizado una compra", "Hola, este es el detalle de sucompra ...", usr.getEmail());
    }
}
