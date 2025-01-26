package com.examen.jeffersonpantat1pregunta2.service;

import com.examen.jeffersonpantat1pregunta2.model.Venta;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VentaService {
     ResponseEntity<String> guardarVenta(Venta venta);

    ResponseEntity<String> registrarVentas(List<Venta> ventas);

    void procesarVentas();
}
