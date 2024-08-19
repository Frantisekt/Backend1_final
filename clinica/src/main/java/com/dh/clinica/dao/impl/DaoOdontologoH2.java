package com.dh.clinica.dao.impl;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.model.Domicilio;
import com.dh.clinica.model.Odontologo;
import com.dh.clinica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Repository
public class DaoOdontologoH2 implements IDao<Odontologo> {
    public static final Logger logger = LoggerFactory.getLogger(DaoOdontologoH2.class);
    private static final String INSERT = "INSERT INTO ODONTOLOGOS VALUES (DEFAULT, ?,?,?)";
    private static final String SELECT_BY_ID = "SELECT * FROM ODONTOLOGOS WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoARetornar = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement prst = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            prst.setInt(1, odontologo.getMatricula());
            prst.setString(2, odontologo.getNombre());
            prst.setString(3, odontologo.getApellido());
            prst.executeUpdate();
            connection.commit();

            //Recuperar la Key generada
            ResultSet rs = prst.getGeneratedKeys();
            Integer id = null;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            odontologoARetornar = new Odontologo(id, odontologo.getMatricula(), odontologo.getNombre(),
                    odontologo.getApellido());

            logger.info("odontologo persistido " + odontologoARetornar);

        } catch (Exception e) {
            logger.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(e.getMessage());
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(e.getMessage());
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return odontologoARetornar;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Connection connection = null;
        Odontologo odontologoEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Integer IdBD = resultSet.getInt(1);
                Integer matricula = resultSet.getInt(2);
                String nombre = resultSet.getString(3);
                String apellido = resultSet.getString(4);

                odontologoEncontrado = new Odontologo(IdBD, matricula, nombre, apellido);
            }
            if(odontologoEncontrado != null){
                logger.info("odontologo encontrado "+ odontologoEncontrado);
            }else logger.info("odontologo no encontrado");

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return odontologoEncontrado;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo = null;

        try {
            connection = H2Connection.getConnection();
            Statement statemnent = connection.createStatement();
            ResultSet rs = statemnent.executeQuery(SELECT_ALL);

            while (rs.next()) {
                Integer id = rs.getInt(1);
                Integer matricula = rs.getInt(2);
                String nombre = rs.getString(3);
                String apellido = rs.getString(4);
                odontologo = new Odontologo(id, matricula, nombre, apellido);
                logger.info(String.valueOf(odontologo));
                odontologos.add(odontologo);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
        return odontologos;
    }

}
