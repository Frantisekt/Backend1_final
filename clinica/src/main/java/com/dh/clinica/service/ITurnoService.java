package com.dh.clinica.service;

import com.dh.clinica.dto.request.TurnoModificarDto;
import com.dh.clinica.dto.request.TurnoRequestDto;
import com.dh.clinica.dto.response.TurnoResponseDto;
import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    TurnoResponseDto guardarTurno(TurnoRequestDto turnoRequestDto);

    Optional<TurnoResponseDto> buscarPorId(Integer id);

    List<TurnoResponseDto> buscarTodos();

    void modificarTurno(TurnoModificarDto turnoModificarDto);
    void eliminarTurno(Integer id);
    List<Turno> buscarTurnoPaciente(String apellidoPaciente);
    List<Turno> buscarTurnoOdontologo(String matriculaOdontologo);
}
