package com.dh.clinica.controller;

import com.dh.clinica.entity.HistorialClinico;
import com.dh.clinica.service.impl.IHistorialClinicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/historiales")
public class HistorialClinicoController {
    @Autowired
    private IHistorialClinicoService historialService;

    @PostMapping
    public ResponseEntity<HistorialClinico> registrar(@Valid @RequestBody HistorialClinico historial) {
        return ResponseEntity.ok(historialService.registrar(historial));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistorialClinico> buscarPorId(@PathVariable Integer id) {
        Optional<HistorialClinico> historialOptional = historialService.buscarPorId(id);
        return historialOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        historialService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<HistorialClinico> actualizar(@Valid @RequestBody HistorialClinico historial) {
        return ResponseEntity.ok(historialService.actualizar(historial));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<HistorialClinico> buscarPorPaciente(@PathVariable Integer pacienteId) {
        Optional<HistorialClinico> historialOptional = historialService.buscarPorPaciente(pacienteId);
        return historialOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
