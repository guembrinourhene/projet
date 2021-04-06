package com.bezkoder.spring.jwt.mongodb.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bezkoder.spring.jwt.mongodb.security.services.JavaMailSenderService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
@RequestMapping("/api/mail")
@Controller
public class MailController {
	@Autowired
private JavaMailSenderService javaMailSenderService;
@PostMapping("/")
public String sendMail(@RequestParam String email ,@RequestParam String text, @RequestParam String projectName) {
	String subject="Votre lien" ;
	try {
		return javaMailSenderService.sendSimpleMessageToNewUser(email,subject, text, projectName);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		return e.getMessage();
	}
	
}
}
