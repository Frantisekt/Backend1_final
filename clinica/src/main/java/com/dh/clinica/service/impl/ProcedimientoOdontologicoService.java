package com.dh.clinica.service.impl;

import com.dh.clinica.entity.ProcedimientoOdontologico;
import com.dh.clinica.repository.IProcedimientoOdontologicoRepository;
import com.dh.clinica.service.impl.IProcedimientoOdontologicoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcedimientoOdontologicoService implements IProcedimientoOdontologicoService {
    private final Logger logger = LoggerFactory.getLogger(ProcedimientoOdontologicoService.class);
    
    @Autowired
    private IProcedimientoOdontologicoRepository procedimientoRepository;

    @Override
    public ProcedimientoOdontologico registrar(ProcedimientoOdontologico procedimiento) {
        logger.info("Iniciando registro de nuevo procedimiento para consulta ID: {}", 
            procedimiento.getConsulta().getId());
        ProcedimientoOdontologico procedimientoGuardado = procedimientoRepository.save(procedimiento);
        logger.info("Procedimiento registrado exitosamente con ID: {}", procedimientoGuardado.getId());
        return procedimientoGuardado;
    }

    @Override
    public Optional<ProcedimientoOdontologico> buscarPorId(Integer id) {
        logger.info("Buscando procedimiento con ID: {}", id);
        Optional<ProcedimientoOdontologico> procedimiento = procedimientoRepository.findById(id);
        if (procedimiento.isPresent()) {
            logger.info("Procedimiento encontrado con ID: {}", id);
        } else {
            logger.warn("No se encontró procedimiento con ID: {}", id);
        }
        return procedimiento;
    }

    @Override
    public List<ProcedimientoOdontologico> listarTodos() {
        logger.info("Listando todos los procedimientos");
        List<ProcedimientoOdontologico> procedimientos = procedimientoRepository.findAll();
        logger.info("Se encontraron {} procedimientos", procedimientos.size());
        return procedimientos;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando procedimiento con ID: {}", id);
        procedimientoRepository.deleteById(id);
        logger.info("Procedimiento eliminado exitosamente");
    }

    @Override
    public ProcedimientoOdontologico actualizar(ProcedimientoOdontologico procedimiento) {
        logger.info("Actualizando procedimiento con ID: {}", procedimiento.getId());
        ProcedimientoOdontologico procedimientoActualizado = procedimientoRepository.save(procedimiento);
        logger.info("Procedimiento actualizado exitosamente");
        return procedimientoActualizado;
    }

    @Override
    public List<ProcedimientoOdontologico> buscarPorConsulta(Integer consultaId) {
        logger.info("Buscando procedimientos para consulta ID: {}", consultaId);
        List<ProcedimientoOdontologico> procedimientos = procedimientoRepository.findByConsultaId(consultaId);
        logger.info("Se encontraron {} procedimientos para la consulta", procedimientos.size());
        return procedimientos;
    }
}

