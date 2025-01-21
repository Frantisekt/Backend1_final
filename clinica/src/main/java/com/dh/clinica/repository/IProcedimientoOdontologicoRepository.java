package com.dh.clinica.repository;

import com.dh.clinica.entity.ProcedimientoOdontologico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProcedimientoOdontologicoRepository extends JpaRepository<ProcedimientoOdontologico, Integer> {
    List<ProcedimientoOdontologico> findByConsultaId(Integer consultaId);

    @Query("SELECT p FROM ProcedimientoOdontologico p WHERE p.nombre LIKE %:nombre%")
    List<ProcedimientoOdontologico> findByNombreContaining(String nombre);

    List<ProcedimientoOdontologico> findByPiezaDental(String piezaDental);
}
