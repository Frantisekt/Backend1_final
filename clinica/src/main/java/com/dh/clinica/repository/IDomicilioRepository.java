package com.dh.clinica.repository;


import com.dh.clinica.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDomicilioRepository extends JpaRepository <Domicilio, Integer>{
}
