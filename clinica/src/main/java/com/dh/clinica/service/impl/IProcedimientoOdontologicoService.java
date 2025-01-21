package com.dh.clinica.service.impl;

import com.dh.clinica.entity.ProcedimientoOdontologico;

import java.util.List;
import java.util.Optional;

public interface IProcedimientoOdontologicoService {
    ProcedimientoOdontologico registrar(ProcedimientoOdontologico procedimiento);
    Optional<ProcedimientoOdontologico> buscarPorId(Integer id);
    List<ProcedimientoOdontologico> listarTodos();
    void eliminar(Integer id);
    ProcedimientoOdontologico actualizar(ProcedimientoOdontologico procedimiento);
    List<ProcedimientoOdontologico> buscarPorConsulta(Integer consultaId);
}