package com.uniquindio.software.clinica;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.annotation.PostConstruct;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class DemoProyectoClinicaApplication { 
    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoProyectoClinicaApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
