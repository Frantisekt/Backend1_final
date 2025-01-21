package com.dh.clinica.controller;

import com.dh.clinica.entity.Consulta;
import com.dh.clinica.entity.EstadoConsulta;
import com.dh.clinica.service.impl.IConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {
    @Autowired
    private IConsultaService consultaService;

    @PostMapping
    public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.registrarConsulta(consulta));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Integer id) {
        Optional<Consulta> consultaOptional = consultaService.buscarPorId(id);
        return consultaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodas() {
        return ResponseEntity.ok(consultaService.listarTodas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Consulta> actualizar(@Valid @RequestBody Consulta consulta) {
        return ResponseEntity.ok(consultaService.actualizar(consulta));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> buscarPorPaciente(@PathVariable Integer pacienteId) {
        return ResponseEntity.ok(consultaService.buscarPorPaciente(pacienteId));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<Consulta>> buscarPorFecha(@RequestParam LocalDate fecha) {
        return ResponseEntity.ok(consultaService.buscarPorFecha(fecha));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<Consulta>> buscarPorFechas(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        return ResponseEntity.ok(consultaService.buscarPorFechas(fechaInicio, fechaFin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Consulta>> buscarPorEstado(@PathVariable EstadoConsulta estado) {
        return ResponseEntity.ok(consultaService.buscarPorEstado(estado));
    }
}
