package com.uniquindio.software.clinica.servicios.implementaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class CorreoServiceImpl {
	@Autowired
	private JavaMailSender javaMailSender;
	public boolean enviarEmail(String asunto, String contenido, String correoDestino){
		SimpleMailMessage message = new SimpleMailMessage();
		try{
			message.setFrom("jclinica0@gmail.com");
			message.setTo(correoDestino);
			message.setSubject(asunto);
			message.setText(contenido);
			javaMailSender.send(message);
			return true;
		} catch (Exception e){
			e.printStackTrace();
		}
	return false;
	}
}
