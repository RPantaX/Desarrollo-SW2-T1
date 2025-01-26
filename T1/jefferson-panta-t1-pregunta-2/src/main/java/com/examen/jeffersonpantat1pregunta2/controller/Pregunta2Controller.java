package com.examen.jeffersonpantat1pregunta2.controller;

import com.examen.jeffersonpantat1pregunta2.model.Venta;
import com.examen.jeffersonpantat1pregunta2.service.VentaService;
import com.examen.jeffersonpantat1pregunta2.service.impl.VentaServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/t1/pregunta2")
public class Pregunta2Controller {
    @Autowired
    private VentaServiceImpl ventaService;

    @PostMapping
    public ResponseEntity<String> guardarVenta(@RequestBody Venta venta) {
        return ventaService.guardarVenta(venta);
    }
    @GetMapping
    public ResponseEntity<String> procesarVentas() {
        ventaService.procesarVentas();
        return ResponseEntity.ok().body("El envio de ventas ha comenzado");
    }
}
