package com.bezkoder.spring.jwt.mongodb.security.services;

import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jwt.mongodb.models.ERole;
import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.models.Role;
import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;
import com.bezkoder.spring.jwt.mongodb.repository.UserRepository;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

@Service
public class JavaMailSenderService {
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private UserRepository userRepository;

	public String sendSimpleMessageToNewUser(String to, String subject, String text, String projectName)
			throws MessagingException {
		
		Properties props = new Properties();
		SimpleMailMessage message = new SimpleMailMessage();
		props.put("mail.smtp.ssl.trust", "*");
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		emailSender.send(message);

		// test add user to project
		User user = new User();
		
		user.setEmail(to);
		userRepository.insert(user);
		List<Projet> projets = projetRepository.findByTitleContaining(projectName);
        Projet projet = (Projet) projets.toArray()[0];
        projet.addUser(user);
		projetRepository.save(projet);
		return "email sended";
	}
	
	
}
