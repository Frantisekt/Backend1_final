package com.dh.clinica.repository;

import com.dh.clinica.entity.Consulta;
import com.dh.clinica.entity.EstadoConsulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface IConsultaRepository extends JpaRepository<Consulta, Integer> {
    List<Consulta> findByPacienteId(Integer pacienteId);

    List<Consulta> findByOdontologoId(Integer odontologoId);

    List<Consulta> findByFecha(LocalDate fecha);

    @Query("SELECT c FROM Consulta c WHERE c.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Consulta> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);

    List<Consulta> findByEstado(EstadoConsulta estado);

    @Query("SELECT c FROM Consulta c WHERE c.paciente.id = :pacienteId AND c.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Consulta> findByPacienteAndFechaBetween(Integer pacienteId, LocalDate fechaInicio, LocalDate fechaFin);

    @Query("SELECT c FROM Consulta c WHERE c.odontologo.id = :odontologoId " +
           "AND c.fecha = :fecha " +
           "AND c.hora BETWEEN :horaInicio AND :horaFin " +
           "AND c.estado NOT IN ('CANCELADA', 'FINALIZADA')")
    Optional<Consulta> findConsultaConflictiva(
        @Param("odontologoId") Integer odontologoId,
        @Param("fecha") LocalDate fecha,
        @Param("horaInicio") LocalTime horaInicio,
        @Param("horaFin") LocalTime horaFin
    );
}