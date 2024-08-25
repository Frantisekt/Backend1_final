package com.dh.clinica.service;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import com.dh.clinica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoIDao;
    private PacienteService pacienteService;
    private OdontologoService odontologService;

    public TurnoService(IDao<Turno> turnoIDao, PacienteService pacienteService, OdontologoService odontologService) {
        this.turnoIDao = turnoIDao;
        this.pacienteService = pacienteService;
        this.odontologService = odontologService;
    }

    public Turno guardarTurno(Turno turno){
        Paciente paciente = pacienteService.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologService.buscarPorId(turno.getOdontologo().getId());
        Turno turnoARetornar = null;
        if (paciente != null && odontologo != null) {
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            turnoARetornar = turnoIDao.guardar(turno);
        }
        return turnoARetornar;
    }
    public Turno buscarPorId(Integer id){
        return turnoIDao.buscarPorId(id);
    }

    public List<Turno> buscarTodos(){
        return turnoIDao.buscarTodos();
    }
    public void modificarTurno(Turno turno){
        turnoIDao.modificar(turno);
    }

    public void eliminarTurno(Integer id){
        turnoIDao.eliminar(id);
    }

}
