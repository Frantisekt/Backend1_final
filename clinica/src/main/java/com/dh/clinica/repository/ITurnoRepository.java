package com.dh.clinica.repository;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("Select t from Turno t join t.paciente p where p.apellido = :apellidoPaciente")
    List<Turno> buscarTurnoPorApellidoPaciente(String apellidoPaciente);

    @Query("Select t from Turno t join t.odontologo p where p.matricula = :matriculaOdontologo")
    List<Turno> buscarTurnoPorMatriculaOdontologo(String matriculaOdontologo);

    @Query("SELECT t FROM Turno t WHERE t.odontologo.id = :odontologoId AND t.fecha = :fecha AND t.hora = :hora")
    Optional<Turno> findConflictingTurno(@Param("odontologoId") Integer odontologoId,
                                         @Param("fecha") LocalDate fecha,
                                         @Param("hora") LocalTime hora);

}
