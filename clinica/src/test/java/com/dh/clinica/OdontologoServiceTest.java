package com.dh.clinica;

import com.dh.clinica.dao.impl.DaoOdontologoH2;
import com.dh.clinica.model.Odontologo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dh.clinica.service.OdontologoService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

     static Logger logger = LoggerFactory.getLogger(OdontologoServiceTest.class);

    OdontologoService odontologoService = new OdontologoService(new DaoOdontologoH2());

    @BeforeAll
    static void createTablas(){
        Connection connection = null;
        try{
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:./clinicac6;INIT=RUNSCRIPT FROM './clinica/create.sql'", "sa","sa");
        }catch (Exception e){
            logger.error(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Testear que un Odontologo se guarde correctamente en la base de datos.")
    void caso1() {
        //dado
        Odontologo odontologo = new Odontologo(25338, "BOYD", "CROWDER");
        Odontologo odontologoDesdeLaBD = odontologoService.guardarOdontologo(odontologo);
        assertNotNull(odontologoDesdeLaBD.getId());
    }

    @Test
    @DisplayName("Testear que se muestren todos los odontologos de la bd.")
    void caso2() {
        //dado
        List<Odontologo> Odontologos = new ArrayList<>();
        Odontologos = odontologoService.buscarTodos();
        assertTrue(!Odontologos.isEmpty());
    }
}
