package com.dh.clinica.service.impl;

import com.dh.clinica.entity.HistorialClinico;

import java.util.Optional;  

public interface IHistorialClinicoService {
    HistorialClinico registrar(HistorialClinico historial);
    Optional<HistorialClinico> buscarPorId(Integer id);
    Optional<HistorialClinico> buscarPorPaciente(Integer pacienteId);
    void eliminar(Integer id);
    HistorialClinico actualizar(HistorialClinico historial);
}