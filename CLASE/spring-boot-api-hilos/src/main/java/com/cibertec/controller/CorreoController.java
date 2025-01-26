package com.cibertec.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.Correo;
import com.cibertec.repository.CorreoRepository;
import com.cibertec.serviceImplement.CorreoServiceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/hilos")
public class CorreoController {

	@Autowired
	private CorreoServiceImplement service;
	
	@PostMapping
	public ResponseEntity<String> registrarCorreo(@RequestBody Correo correo) {
		return service.registrarCorreo(correo);
	}
	
	@GetMapping
	public ResponseEntity<String> procesarCorreos() {
		service.procesarCorreos();
		return ResponseEntity.ok().body("El envio de correos ha comenzado");
	}
	
	
	
}
