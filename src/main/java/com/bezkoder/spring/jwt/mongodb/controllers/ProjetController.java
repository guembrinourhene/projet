package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jwt.mongodb.models.Projet;
import com.bezkoder.spring.jwt.mongodb.repository.ProjetRepository;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/api/projets")
public class ProjetController {
  

	@Autowired
	  ProjetRepository projetRepository;

	  @GetMapping("")	
	  @CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<List<Projet>> getAllTutorials(@RequestParam(required = false) String title) {
	    try {
	      List<Projet> tutorials = new ArrayList<Projet>();

	      if (title == null)
	    	  projetRepository.findAll().forEach(tutorials::add);
	      else
	    	  projetRepository.findByTitleContaining(title).forEach(tutorials::add);

	      if (tutorials.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(tutorials, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @GetMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> getTutorialById(@PathVariable("id") String id) {
	    Optional<Projet> tutorialData =  projetRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @PostMapping("/pro")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> createProjet(@RequestBody Projet tutorial) throws Exception{
	    try {
	    	Projet project = new Projet();
	    	project.setDescription(tutorial.getDescription());
	    	project.setTitle(tutorial.getTitle());
	       projetRepository.insert(project);
	      return new ResponseEntity<>(project, HttpStatus.CREATED);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	throw new Exception(e.getMessage());	    }
	  }

	  @PutMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<Projet> updateTutorial(@PathVariable("id") String id, @RequestBody Projet tutorial) {
	    Optional<Projet> tutorialData =  projetRepository.findById(id);

	    if (tutorialData.isPresent()) {
	      Projet _tutorial = tutorialData.get();
	      _tutorial.setTitle(tutorial.getTitle());
	      _tutorial.setDescription(tutorial.getDescription());
	     
	      
	      return new ResponseEntity<>( projetRepository.save(_tutorial), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/{id}")
		@CrossOrigin(origins="http://localhost:4200")
	  public void deleteTutorial(@PathVariable("id") String id) throws Exception{
	    try {	
	      projetRepository.deleteById(id);
	    } catch (Exception e) {
	    	throw new Exception(e.getMessage());	    }
	  }

	  @DeleteMapping("/projets")
		@CrossOrigin(origins="http://localhost:4200")
	  public ResponseEntity<HttpStatus> deleteAllTutorials() throws Exception{
	    try {
	    	 projetRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
throw new Exception(e.getMessage());
}
	  }
}
