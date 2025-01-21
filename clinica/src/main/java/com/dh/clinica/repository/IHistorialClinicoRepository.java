package com.dh.clinica.repository;

import com.dh.clinica.entity.HistorialClinico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IHistorialClinicoRepository extends JpaRepository<HistorialClinico, Integer> {
    Optional<HistorialClinico> findByPacienteId(Integer pacienteId);

    @Query("SELECT h FROM HistorialClinico h WHERE h.paciente.dni LIKE :dni")
    Optional<HistorialClinico> findByPacienteDni(String dni);

    @Query("SELECT h FROM HistorialClinico h WHERE h.alergias LIKE %:alergia%")
    List<HistorialClinico> findByAlergiasContaining(String alergia);
}
