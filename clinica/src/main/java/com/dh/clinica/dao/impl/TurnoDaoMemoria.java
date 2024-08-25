package com.dh.clinica.dao.impl;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDaoMemoria implements IDao<Turno> {
    public static final Logger logger = LoggerFactory.getLogger(TurnoDaoMemoria.class);
    private List<Turno> turnos = new ArrayList<>();
    @Override
    public Turno guardar(Turno turno) {
        turno.setId(turnos.size()+1);
        turnos.add(turno);
        logger.info("turno agregado "+ turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        Turno turnoARetornar = null;
        for(Turno t: turnos){
            if(t.getId().equals(id)){
                turnoARetornar = t;
                logger.info("turno encontrado "+ turnoARetornar);
            }
        }
        return turnoARetornar;
    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("turnos: "+turnos);
        return turnos;
    }

    @Override
    public void modificar(Turno turno) {
        for(Turno t: turnos){
            if(t.getId().equals(turno.getId())){
                t.setOdontologo(turno.getOdontologo());
                t.setPaciente(turno.getPaciente());
                t.setFecha(turno.getFecha());
                logger.info("turno modificado "+ t);
            }
        }
    }

    @Override
    public void eliminar(Integer id) {
        for(Turno t: turnos){
            if(t.getId().equals(id)){
                logger.info("turno eliminado "+ t);
                turnos.remove(t);
                break;
            }
        }
    }
}
