package com.dh.clinica.dao.impl;

import com.dh.clinica.dao.IDao;
import com.dh.clinica.db.H2Connection;
import com.dh.clinica.model.Domicilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

public class DomicilioDaoH2 implements IDao<Domicilio> {
    public static final Logger logger = LoggerFactory.getLogger(DomicilioDaoH2.class);
    public static final String INSERT = "INSERT INTO DOMICILIOS VALUES (DEFAULT,?,?,?,?)";
    public static final String SELECT_ID = "SELECT * FROM DOMICILIOS WHERE ID = ?";
    @Override
    public Domicilio guardar(Domicilio domicilio) {
        Connection connection = null;
        Domicilio domicilioARetornar = null;
        try{
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, domicilio.getCalle());
            preparedStatement.setInt(2, domicilio.getNumero());
            preparedStatement.setString(3, domicilio.getLocalidad());
            preparedStatement.setString(4, domicilio.getProvincia());
            preparedStatement.executeUpdate();
            connection.commit();
            // traer la clave primaria
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                Integer id = resultSet.getInt(1);
                domicilioARetornar = new Domicilio(id, domicilio.getCalle(), domicilio.getNumero(),
                        domicilio.getLocalidad(), domicilio.getProvincia());
            }
            logger.info("domicilio persistido "+  domicilioARetornar);

        }catch (Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    logger.error(ex.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return domicilioARetornar;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        Connection connection = null;
        Domicilio domicilioEncontrado = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               Integer idDb = resultSet.getInt(1);
               String calle = resultSet.getString(2);
               int numero = resultSet.getInt(3);
               String localidad = resultSet.getString(4);
               String provincia = resultSet.getString(5);
               domicilioEncontrado = new Domicilio(idDb, calle, numero, localidad, provincia);
            }
            if(domicilioEncontrado !=null){
                logger.info("domicilio encontrado "+ domicilioEncontrado);
            } else logger.info("domicilio no encontrado");


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
        return domicilioEncontrado;
    }

    @Override
    public List<Domicilio> buscarTodos() {
        return null;
    }
}
