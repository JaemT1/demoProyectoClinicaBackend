package com.uniquindio.software.clinica.servicios.implementaciones;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class CorreoServiceImpl {
	@Autowired
	private JavaMailSender javaMailSender;
	public boolean enviarEmail(String asunto, String contenido, String correoDestino){
		MimeMessage mensaje = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mensaje);
		try{
			helper.setSubject(asunto);
			helper.setText(contenido, true);
			helper.setTo(correoDestino);
			helper.setFrom("jclinica0@gmail.com");
			javaMailSender.send(mensaje);
			return true;
		} catch (Exception e){
			e.printStackTrace();
		}




	return false;
	}
}
