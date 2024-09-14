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
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorId(Integer id) {
        return odontologoRepository.findById(id);
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
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarPorApellidoyNombre(String apellido, String nombre) {
        return odontologoRepository.findByApellidoAndNombre(apellido, nombre);
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
        return odontologoRepository.findByMatriculaLike(matricula);
    }
}
