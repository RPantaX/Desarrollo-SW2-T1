package com.examen.jeffersonpantat1pregunta2.repository;

import com.examen.jeffersonpantat1pregunta2.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long>{
    List<Venta> findByEstadoFalse();
}
