package com.examen.jeffersonpantat1pregunta2.service.impl;

import com.examen.jeffersonpantat1pregunta2.model.Venta;
import com.examen.jeffersonpantat1pregunta2.repository.VentaRepository;
import com.examen.jeffersonpantat1pregunta2.service.VentaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private Executor executor;

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VentaServiceImpl.class);
    @Override
    public ResponseEntity<String> guardarVenta(Venta venta) {
        venta.setEstado(false);
        ventaRepository.save(venta);

        return ResponseEntity.status(HttpStatus.CREATED).body("El registro de ventas ha comenzado. - Iterador: " + venta.getId());
    }
    @Override
    public ResponseEntity<String> registrarVentas(List<Venta> ventas) {
        for (Venta venta : ventas) {
            guardarVenta(venta);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Se han registrado 8 ventas.");
    }
    @Async("ventaTaskExecutor")
    public void procesarVentas() {
        List<Venta> ventasPendientes = ventaRepository.findByEstadoFalse();
        log.info("Procesando ventas en el hilo: " + Thread.currentThread().getName());
        log.info("Cantidad a procesar: " + ventasPendientes.size());

        for (Venta venta : ventasPendientes) {
            executor.execute(() -> {
                try {
                    log.info("Procesando venta de producto: " + venta.getProducto() + " en hilo: " + Thread.currentThread().getName());
                    Thread.sleep(1000);
                    venta.setEstado(true);
                    ventaRepository.save(venta);
                    log.info("Venta procesada del producto: " + venta.getProducto() + " en hilo: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    log.error("Error al procesar venta del producto " + venta.getProducto() + ": " + e.getMessage());
                }
            });
        }
        log.info("Todas las ventas han sido procesadas.");
    }
}
