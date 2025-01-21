package com.dh.clinica.service.impl;

import com.dh.clinica.entity.HistorialClinico;
import com.dh.clinica.repository.IHistorialClinicoRepository;
import com.dh.clinica.service.impl.IHistorialClinicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistorialClinicoService implements IHistorialClinicoService {
    private final Logger logger = LoggerFactory.getLogger(HistorialClinicoService.class);
    
    @Autowired
    private IHistorialClinicoRepository historialRepository;

    @Override
    public HistorialClinico registrar(HistorialClinico historial) {
        logger.info("Iniciando registro de nuevo historial clínico para paciente ID: {}", 
            historial.getPaciente().getId());
        HistorialClinico historialGuardado = historialRepository.save(historial);
        logger.info("Historial clínico registrado exitosamente con ID: {}", historialGuardado.getId());
        return historialGuardado;
    }

    @Override
    public Optional<HistorialClinico> buscarPorId(Integer id) {
        logger.info("Buscando historial clínico con ID: {}", id);
        Optional<HistorialClinico> historial = historialRepository.findById(id);
        if (historial.isPresent()) {
            logger.info("Historial clínico encontrado con ID: {}", id);
        } else {
            logger.warn("No se encontró historial clínico con ID: {}", id);
        }
        return historial;
    }

    @Override
    public Optional<HistorialClinico> buscarPorPaciente(Integer pacienteId) {
        logger.info("Buscando historial clínico para paciente ID: {}", pacienteId);
        Optional<HistorialClinico> historial = historialRepository.findByPacienteId(pacienteId);
        if (historial.isPresent()) {
            logger.info("Historial clínico encontrado para paciente ID: {}", pacienteId);
        } else {
            logger.warn("No se encontró historial clínico para paciente ID: {}", pacienteId);
        }
        return historial;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando historial clínico con ID: {}", id);
        historialRepository.deleteById(id);
        logger.info("Historial clínico eliminado exitosamente");
    }

    @Override
    public HistorialClinico actualizar(HistorialClinico historial) {
        logger.info("Actualizando historial clínico con ID: {}", historial.getId());
        HistorialClinico historialActualizado = historialRepository.save(historial);
        logger.info("Historial clínico actualizado exitosamente");
        return historialActualizado;
    }
}
