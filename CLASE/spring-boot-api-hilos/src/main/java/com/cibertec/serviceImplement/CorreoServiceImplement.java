package com.cibertec.serviceImplement;

import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cibertec.model.Correo;
import com.cibertec.repository.CorreoRepository;
import com.cibertec.service.CorreoService;

@Service
public class CorreoServiceImplement implements CorreoService{

	@Autowired
	private CorreoRepository correoRepository;
	
	@Autowired
	private Executor correoTaskExecutor;
	
	@Override
	public ResponseEntity<String> registrarCorreo(Correo correo) {
		correo.setEnviado(false);
		correoRepository.save(correo);
		return ResponseEntity.status(HttpStatus.CREATED).body("El envio de correos ha comenzado. - Iterador: " + correo.getId() );
	}

	@Async("correoTaskExecutor")
	public void procesarCorreos() {
		List<Correo> correosPendientes = correoRepository.findByEnviadoFalse();
		System.out.println("Procesando correos en el hilo: " + Thread.currentThread().getName());
		System.out.println("Cantidad a procesar: " + correosPendientes.size());

		for (Correo correo : correosPendientes) {
			correoTaskExecutor.execute(() -> {
				
				try {
                    System.out.println("Enviando correo a: " + correo.getDestinatario() + " en hilo: " + Thread.currentThread().getName());
                    //System.out.println("Enviando correo a: " + correo.getDestinatario());
					Thread.sleep(2000);
					correo.setEnviado(true);
					correoRepository.save(correo);
					System.out.println("Correo enviado a: " + correo.getDestinatario() + " en hilo: " + Thread.currentThread().getName());	
					//System.out.println("Correo enviado a: " + correo.getDestinatario());
					
				} catch (InterruptedException e) {
					System.err.println("Error al enviar correo a " + correo.getDestinatario() + ": " + e.getMessage());
				}
			});
			
		}
		System.out.println("Todos los correos han sido procesados.");
		//return ResponseEntity.ok().body("El envío de correos ha comenzado.");
		
	}

}
