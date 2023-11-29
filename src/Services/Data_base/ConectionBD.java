package Services.Data_base;

import Domain.Dto.Menu;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConectionBD {
    private String user = "postgres";
    private String password = "1234";
    private Connection conexion;
    public Connection conectar() throws SQLException {
                conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Market", user, password);
               System.out.println("*/*/*/ CONECTION SUCESSFULLY /*/*");
                return conexion;
    }



}

