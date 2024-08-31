package com.dh.clinica.service;

import com.dh.clinica.entity.Odontologo;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardarOdontologo (Odontologo odontologo);

    List<Odontologo> buscarTodos() ;

    Optional<Odontologo> buscarPorId(Integer id) ;

    void modificarodontolgo(Odontologo odontologo);

   void eliminarodontolgo(Integer id);

    List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre);

    @Query("Select p from Odontologo p where p.nombre LIKE %:nombre%")
    List<Odontologo> buscarLikeNombre(String nombre);

    Optional<Odontologo> buscarLikeMatricula(String matricula);
}