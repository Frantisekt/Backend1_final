package com.dh.clinica.repository;

import com.dh.clinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("SELECT p FROM Paciente p WHERE p.apellido LIKE %:apellido% AND p.nombre LIKE %:nombre%")
    List<Paciente> findByApellidoAndNombre(String apellido, String nombre);

    @Query("Select p from Paciente p where p.nombre LIKE %:parteNombre% ")
    List<Paciente> findByNombreLike(String parteNombre);

    Optional<Paciente> findByDniLike(String dni);


}
