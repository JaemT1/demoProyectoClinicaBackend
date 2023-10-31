package com.uniquindio.software.clinica.servicios.implementaciones;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class CorreoServicio {
	@Autowired
	private JavaMailSender javaMailSender;
	public boolean enviarEmail(String asunto, String contenido, String destinatario){
		MimeMessage mensaje = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mensaje);
		try{
			helper.setSubject(asunto);
			helper.setText(contenido, true);
			helper.setTo(destinatario);
			helper.setFrom("no_reply@dominio.com");
			javaMailSender.send(mensaje);
			return true;
		} catch (Exception e){
			e.printStackTrace();
		}
	
	return false;
	
	}

}
