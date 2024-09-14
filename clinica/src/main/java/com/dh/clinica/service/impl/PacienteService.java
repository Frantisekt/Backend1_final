package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Paciente;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IPacienteRepository;
import com.dh.clinica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private final Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        logger.info("Paciente guardado correctamente");
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> buscarPorId(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()){
            logger.info("Paciente encontrado: " + pacienteEncontrado);
            return pacienteEncontrado;
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("paciente no encontrado");
            //ResponseEntity.notFound().build();
            logger.info("No se encontro el paciente:  Id:" + id + " Not found.");
            throw new ResourceNotFoundException("No se encontro el paciente: " + id + " Not found.");
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if(pacientes.isEmpty()){
            logger.info("No se encontraron pacientes.");
            throw new ResourceNotFoundException("No se encontraron pacientes.");
        }else{
            logger.info("Numero de pacientes encontrados: " + pacientes.size());
            return pacientes;
        }
    }

    @Override
    public void modificarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(paciente.getId());
        if(pacienteEncontrado.isPresent()) {
            logger.info("Paciente modificado correctamente");
            pacienteRepository.save(paciente);
        }else{
            logger.info("El paciente no fue encontrado. Id: " + paciente.getId() + " Not found");
            throw new ResourceNotFoundException("El paciente no fue encontrado. Id: " + paciente.getId() + " Not found");
        }
    }

    @Override
    public void eliminarPaciente(Integer id) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findById(id);
        if(pacienteEncontrado.isPresent()) {
            logger.info("Paciente eliminado correctamente");
            pacienteRepository.deleteById(id);
        }else{
            logger.info("El paciente no fue encontrado. Id: " + id + " Not found");
            throw new ResourceNotFoundException("El paciente no fue encontrado. Id: " + id + " Not found");
        }
    }

    @Override
    public List<Paciente> buscarPorApellidoyNombre(String apellido, String nombre) {
        List<Paciente> pacientes = pacienteRepository.findByApellidoAndNombre(apellido, nombre);
        if(pacientes.isEmpty()){
            logger.info("No se encontraron pacientes.");
            throw new ResourceNotFoundException("No se encontraron pacientes.");
        }else{
            logger.info("Numero de pacientes encontrados: " + pacientes.size());
            return pacientes;
        }
    }

    @Override
    public List<Paciente> buscarLikeNombre(String nombre) {
        List<Paciente> pacientes = pacienteRepository.findByNombreLike(nombre);
        if(pacientes.isEmpty()){
            logger.info("No se encontraron pacientes que coincidan con: '" +nombre + "'");
            throw new ResourceNotFoundException("No se encontraron pacientes.");
        }else{
            logger.info("Numero de pacientes encontrados: " + pacientes.size());
            return pacientes;
        }
    }

    @Override
    public Optional<Paciente> buscarLikeDni(String dni) {
        Optional<Paciente> pacienteEncontrado = pacienteRepository.findByDniLike(dni);
        if(pacienteEncontrado.isPresent()) {
            logger.info("Paciente eliminado correctamente");
            return pacienteEncontrado;
        }else{
            logger.info("El paciente no fue encontrado. Dni: " + dni   + " Not found");
            throw new ResourceNotFoundException("El paciente no fue encontrado. Dni: " + dni   + " Not found");
        }
    }

}
