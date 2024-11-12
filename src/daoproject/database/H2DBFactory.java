/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject.database;

/**
 *
 * @author User
 */
// H2DBFactory.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2DBFactory extends DBFactory {
    @Override
    public Connection getConnection() throws SQLException {
        String url = "jdbc:h2:~/test"; // Ou outro caminho para o seu banco H2
        String user = "sa";
        String password = "";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(H2DBFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return DriverManager.getConnection(url, user, password);
    }
}

