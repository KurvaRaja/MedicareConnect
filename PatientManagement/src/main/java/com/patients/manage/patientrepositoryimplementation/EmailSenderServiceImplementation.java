package com.patients.manage.patientrepositoryimplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImplementation
{
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
	String fromEmail;
	
	public String sendEmail( String toEmail,String username,Long token)
	{
		SimpleMailMessage message=new SimpleMailMessage();
		
		
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setSubject("Notification:Your Appointment was Succesfull");
		message.setText("Dear "+username+"\n Thanks for visting our hospital,\n your appointment was sucessfull,Please come within time slot allocated/n Your token Id is "+token);
		mailSender.send(message);
		return "we have sent mail with Token Id succesfully";
	}
	

}
