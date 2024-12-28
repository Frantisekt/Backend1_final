package com.dh.clinica.service.impl;
import com.dh.clinica.exception.TurnoConflictException;
import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.OdontologoResponseDto;
import com.dh.clinica.dto.response.PacienteResponseDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.ITurnoRepository;
import com.dh.clinica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {
    private final Logger logger = LoggerFactory.getLogger(TurnoService.class);
    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologService;
    @Autowired
    private ModelMapper modelMapper;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologService = odontologService;
    }

    @Override
    public TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto){
        Optional<Paciente> paciente = pacienteService.buscarPorId(turnoRequestDto.getPaciente_id());
        Optional<Odontologo> odontologo = odontologService.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turno = new Turno();
        Turno turnoDesdeDb = null;
        TurnoResponseDto turnoARetornar = null;
        if (paciente.isPresent() && odontologo.isPresent()) {
            LocalDate fecha = LocalDate.parse(turnoRequestDto.getFecha());
            LocalTime hora = LocalTime.parse(turnoRequestDto.getHora());

            // Verificar si ya existe un turno para esa fecha y hora
            Optional<Turno> turnoConflictivo = turnoRepository.findConflictingTurno(
                    odontologo.get().getId(), fecha, hora
            );

            if (turnoConflictivo.isPresent()) {
                throw new TurnoConflictException("Ya existe un turno para este odontólogo en la fecha y hora especificadas.");
            }
            
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(fecha);
            turno.setHora(hora);
            
            turnoDesdeDb = turnoRepository.save(turno);
            turnoARetornar = mapearATurnoResponse(turnoDesdeDb);
        }
        return turnoARetornar;
    }

    @Override
    public Optional<TurnoResponseDto> buscarPorId(Integer id) {
        Optional<Turno> turnoDesdeDb = turnoRepository.findById(id);
        TurnoResponseDto turnoResponseDto = null;
        if(turnoDesdeDb.isPresent()){
            turnoResponseDto = mapearATurnoResponse(turnoDesdeDb.get());
        }
        return Optional.ofNullable(turnoResponseDto);
    }

    @Override
    public List<TurnoResponseDto> buscarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDto> turnosRespuesta = new ArrayList<>();
        for (Turno t: turnos){
            TurnoResponseDto turnoAuxiliar = mapearATurnoResponse(t);
            turnosRespuesta.add(turnoAuxiliar);
        }
        return turnosRespuesta;
    }

    @Override
    public void modificarTurno(TurnoModificarDto turnoModificarDto) {
        Turno turnoExistente = turnoRepository.findById(turnoModificarDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado"));

        Paciente paciente = pacienteService.buscarPorId(turnoModificarDto.getPaciente_id())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Odontologo odontologo = odontologService.buscarPorId(turnoModificarDto.getOdontologo_id())
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo no encontrado"));

        turnoExistente.setPaciente(paciente);
        turnoExistente.setOdontologo(odontologo);
        turnoExistente.setFecha(LocalDate.parse(turnoModificarDto.getFecha()));
        turnoExistente.setHora(LocalTime.parse(turnoModificarDto.getHora()));

        turnoRepository.save(turnoExistente);
    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoRepository.deleteById(id);
    }

    private TurnoResponseDto convertirTurnoAResponse(Turno turnoDesdeDb){
        OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto(
                turnoDesdeDb.getOdontologo().getId(), turnoDesdeDb.getOdontologo().getMatricula(),
                turnoDesdeDb.getOdontologo().getNombre(), turnoDesdeDb.getOdontologo().getApellido(),
                turnoDesdeDb.getOdontologo().getNombre()
        );

        PacienteResponseDto pacienteResponseDto = new PacienteResponseDto(
                turnoDesdeDb.getPaciente().getId(), turnoDesdeDb.getPaciente().getNombre(),
                turnoDesdeDb.getPaciente().getApellido(), turnoDesdeDb.getPaciente().getDni(),
                turnoDesdeDb.getPaciente().getEmail()
        );

        TurnoResponseDto turnoARetornar = new TurnoResponseDto(
                turnoDesdeDb.getId(), 
                pacienteResponseDto, 
                odontologoResponseDto,
                turnoDesdeDb.getFecha().toString(),
                turnoDesdeDb.getHora().toString()
        );
        return turnoARetornar;
    }

    private TurnoResponseDto mapearATurnoResponse(Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setOdontologoResponseDto(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
        turnoResponseDto.setPacienteResponseDto(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        return turnoResponseDto;
    }

    @Override
    public List<Turno> buscarTurnoPaciente(String apellidoPaciente){
        return turnoRepository.buscarTurnoPorApellidoPaciente(apellidoPaciente);
    }

    @Override
    public List<Turno> buscarTurnoOdontologo(String matriculaOdontologo){
        List<Turno> turnosEncontrados = turnoRepository.buscarTurnoPorMatriculaOdontologo(matriculaOdontologo);
        if (turnosEncontrados.isEmpty()) {
            logger.info("No se encontraron turnos para este odontologo");
            throw new ResourceNotFoundException("No se encontraron turnos para este odontologo");
        }else {
            logger.info("turnos encontrados: " + turnosEncontrados.size());
            return turnosEncontrados;
        }
    }

}
