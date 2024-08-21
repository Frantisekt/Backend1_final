package com.dh.clinica.db;

import com.dh.clinica.dao.impl.PacienteDaoH2;
import com.dh.clinica.dao.impl.DaoOdontologoH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public static final Logger logger = LoggerFactory.getLogger(H2Connection.class);
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./clinicac6","sa","sa");
    }

    public static void crearTablas(){
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinicac6;INIT=RUNSCRIPT FROM './clinica/create.sql'", "sa", "sa");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
