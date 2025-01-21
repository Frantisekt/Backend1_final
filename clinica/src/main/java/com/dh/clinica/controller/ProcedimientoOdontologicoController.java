package com.dh.clinica.controller;

import com.dh.clinica.entity.ProcedimientoOdontologico;
import com.dh.clinica.service.impl.IProcedimientoOdontologicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/procedimientos")
public class ProcedimientoOdontologicoController {
    @Autowired
    private IProcedimientoOdontologicoService procedimientoService;

    @PostMapping
    public ResponseEntity<ProcedimientoOdontologico> registrar(
            @Valid @RequestBody ProcedimientoOdontologico procedimiento) {
        return ResponseEntity.ok(procedimientoService.registrar(procedimiento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimientoOdontologico> buscarPorId(@PathVariable Integer id) {
        Optional<ProcedimientoOdontologico> procedimientoOptional = procedimientoService.buscarPorId(id);
        return procedimientoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProcedimientoOdontologico>> listarTodos() {
        return ResponseEntity.ok(procedimientoService.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        procedimientoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ProcedimientoOdontologico> actualizar(
            @Valid @RequestBody ProcedimientoOdontologico procedimiento) {
        return ResponseEntity.ok(procedimientoService.actualizar(procedimiento));
    }

    @GetMapping("/consulta/{consultaId}")
    public ResponseEntity<List<ProcedimientoOdontologico>> buscarPorConsulta(
            @PathVariable Integer consultaId) {
        return ResponseEntity.ok(procedimientoService.buscarPorConsulta(consultaId));
    }
}

