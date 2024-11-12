/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject.database;

/**
 *
 * @author User
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDBFactory extends DBFactory {
    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/nomedobanco";
        String user = "root";
        String password = "gabriel1234d";
        return DriverManager.getConnection(url, user, password);
    }
}
