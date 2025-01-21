package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Consulta;
import com.dh.clinica.entity.EstadoConsulta;
import com.dh.clinica.repository.IConsultaRepository;
import com.dh.clinica.service.impl.IConsultaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dh.clinica.exception.ConsultaConflictException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService implements IConsultaService {
    private final Logger logger = LoggerFactory.getLogger(ConsultaService.class);
    
    @Autowired
    private IConsultaRepository consultaRepository;

    @Override
    public Consulta registrarConsulta(Consulta consulta) {
        logger.info("Iniciando registro de nueva consulta para paciente ID: {} con odontólogo ID: {} para fecha: {} hora: {}", 
            consulta.getPaciente().getId(), 
            consulta.getOdontologo().getId(),
            consulta.getFecha(),
            consulta.getHora());
        
        // Calculamos 15 minutos antes y después de la hora solicitada
        LocalTime horaInicio = consulta.getHora().minusMinutes(15);
        LocalTime horaFin = consulta.getHora().plusMinutes(15);
        
        // Verificar si ya existe una consulta para ese odontólogo en ese rango horario
        Optional<Consulta> consultaConflictiva = consultaRepository.findConsultaConflictiva(
            consulta.getOdontologo().getId(),
            consulta.getFecha(),
            horaInicio,
            horaFin
        );

        if (consultaConflictiva.isPresent()) {
            Consulta conflicto = consultaConflictiva.get();
            logger.warn("Conflicto de horario detectado. Hora solicitada: {}, Hora ocupada: {}", 
                consulta.getHora(), conflicto.getHora());
            throw new ConsultaConflictException(
                String.format("No se puede agendar la consulta a las %s. " +
                            "El odontólogo tiene una consulta programada a las %s. " +
                            "Debe haber al menos 15 minutos entre consultas.", 
                            consulta.getHora(), conflicto.getHora())
            );
        }

        Consulta consultaGuardada = consultaRepository.save(consulta);
        logger.info("Consulta registrada exitosamente con ID: {}", consultaGuardada.getId());
        return consultaGuardada;
    }

    @Override
    public Optional<Consulta> buscarPorId(Integer id) {
        logger.info("Buscando consulta con ID: {}", id);
        Optional<Consulta> consulta = consultaRepository.findById(id);
        if (consulta.isPresent()) {
            logger.info("Consulta encontrada con ID: {}", id);
        } else {
            logger.warn("No se encontró consulta con ID: {}", id);
        }
        return consulta;
    }

    @Override
    public List<Consulta> listarTodas() {
        logger.info("Listando todas las consultas");
        List<Consulta> consultas = consultaRepository.findAll();
        logger.info("Se encontraron {} consultas", consultas.size());
        return consultas;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Eliminando consulta con ID: {}", id);
        consultaRepository.deleteById(id);
        logger.info("Consulta eliminada exitosamente");
    }

    @Override
    public Consulta actualizar(Consulta consulta) {
        logger.info("Actualizando consulta con ID: {}", consulta.getId());
        Consulta consultaActualizada = consultaRepository.save(consulta);
        logger.info("Consulta actualizada exitosamente");
        return consultaActualizada;
    }

    @Override
    public List<Consulta> buscarPorPaciente(Integer pacienteId) {
        logger.info("Buscando consultas para paciente ID: {}", pacienteId);
        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);
        logger.info("Se encontraron {} consultas para el paciente", consultas.size());
        return consultas;
    }

    @Override
    public List<Consulta> buscarPorFecha(LocalDate fecha) {
        logger.info("Buscando consultas para la fecha: {}", fecha);
        List<Consulta> consultas = consultaRepository.findByFecha(fecha);
        logger.info("Se encontraron {} consultas para la fecha especificada", consultas.size());
        return consultas;
    }

    @Override
    public List<Consulta> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        logger.info("Buscando consultas entre {} y {}", fechaInicio, fechaFin);
        List<Consulta> consultas = consultaRepository.findByFechaBetween(fechaInicio, fechaFin);
        logger.info("Se encontraron {} consultas en el rango de fechas", consultas.size());
        return consultas;
    }

    @Override
    public List<Consulta> buscarPorEstado(EstadoConsulta estado) {
        logger.info("Buscando consultas con estado: {}", estado);
        List<Consulta> consultas = consultaRepository.findByEstado(estado);
        logger.info("Se encontraron {} consultas con el estado {}", consultas.size(), estado);
        return consultas;
    }
}