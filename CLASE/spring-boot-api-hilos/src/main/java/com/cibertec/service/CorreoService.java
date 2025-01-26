package com.cibertec.service;

import org.springframework.http.ResponseEntity;

import com.cibertec.model.Correo;

public interface CorreoService {

	public ResponseEntity<String> registrarCorreo(Correo correo);
	
	public void procesarCorreos();
	
}
