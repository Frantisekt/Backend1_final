package com.dh.clinica.service.impl;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.repository.IOdontologoRepository;
import com.dh.clinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

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
        odontologoRepository.save(odontologo);
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
        return odontologoRepository.findByNombreLike(nombre);
    }

    @Override
    public Optional<Odontologo> buscarLikeMatricula(String matricula) {
        return odontologoRepository.findByMatriculaLike(matricula);
    }
}
