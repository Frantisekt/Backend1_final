package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;


@Service
public class OdontologoService implements IOdontologoService {
    private final Logger logger = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository IOdontologoRepository) {
        this.odontologoRepository = IOdontologoRepository;
    }

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Odontologo guardado correctamente");
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if(odontologos.isEmpty()){
            logger.info("No se encontraron odontologos.");
            throw new ResourceNotFoundException("No se encontraron odontologos.");
        }else{
            logger.info("Numero de odontologos encontrados: " + odontologos.size());
            return odontologos;
        }
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()){
            logger.info("Odontologo encontrado: " + odontologoEncontrado);
            return odontologoEncontrado;
        } else {
            // ResponseEntity.status(HttpStatus.NOT_FOUND).body("odontologo no encontrado");
            //ResponseEntity.notFound().build();
            logger.info("No se encontro el odontologo: " + id + " Not found.");
            throw new ResourceNotFoundException("No se encontro el odontologo: " + id + " Not found.");
        }
    }

    @Override
    public void modificarodontolgo(Odontologo odontologo) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(odontologo.getId());
        if(odontologoEncontrado.isPresent()) {
            logger.info("Odontologo modificado correctamente");
            odontologoRepository.save(odontologo);
        }else{
            logger.info("El odontologo no fue encontrado. Id: " + odontologo.getId() + " Not found");
            throw new ResourceNotFoundException("El odontologo no fue encontrado. Id: " + odontologo.getId() + " Not found");
        }
    }

    @Override
    public void eliminarodontolgo(Integer id) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findById(id);
        if(odontologoEncontrado.isPresent()) {
            logger.info("Odontologo eliminado correctamente");
            odontologoRepository.deleteById(id);
        }else{
            logger.info("El odontologo no fue encontrado. Id: " + id + " Not found");
            throw new ResourceNotFoundException("El odontologo no fue encontrado. Id: " + id + " Not found");
        }
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        List<Odontologo> odontologos = odontologoRepository.findByApellidoAndNombre(apellido, nombre);
        if(odontologos.isEmpty()){
            logger.info("No se encontraron odontologos.");
            throw new ResourceNotFoundException("No se encontraron odontologos.");
        }else{
            logger.info("Numero de odontologos encontrados: " + odontologos.size());
            return odontologos;
        }
    }

    @Override
    public List<Odontologo> buscarLikeNombre(String nombre) {
        List<Odontologo> odontologos = odontologoRepository.findByNombreLike(nombre);
        if(odontologos.isEmpty()){
            logger.info("No se encontraron odontologos.");
            throw new ResourceNotFoundException("No se encontraron odontologos.");
        }else{
            logger.info("Numero de odontologos encontrados: " + odontologos.size());
            return odontologos;
        }
    }

    @Override
    public Optional<Odontologo> buscarLikeMatricula(String matricula) {
        Optional<Odontologo> odontologoEncontrado = odontologoRepository.findByMatriculaLike(matricula);
        if(odontologoEncontrado.isPresent()) {
            logger.info("Odontologo encontrado: " + odontologoEncontrado );
            return odontologoEncontrado;
        }else{
            logger.info("El odontologo no fue encontrado. Matricula: " + matricula   + " Not found");
            throw new ResourceNotFoundException("El odontologo no fue encontrado. Matricula: " + matricula   + " Not found");
        }
    }
}
