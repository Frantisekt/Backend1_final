package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Consulta;
import com.dh.clinica.entity.EstadoConsulta;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IConsultaService {
    Consulta registrarConsulta(Consulta consulta);
    Optional<Consulta> buscarPorId(Integer id);
    List<Consulta> listarTodas();
    void eliminar(Integer id);
    Consulta actualizar(Consulta consulta);
    List<Consulta> buscarPorPaciente(Integer pacienteId);
    List<Consulta> buscarPorFecha(LocalDate fecha);
    List<Consulta> buscarPorFechas(LocalDate fechaInicio, LocalDate fechaFin);
    List<Consulta> buscarPorEstado(EstadoConsulta estado);
}
