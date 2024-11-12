/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daoproject.database;

/**
 *
 * @author User
 */
// DBFactory.java
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBFactory {
    public abstract Connection getConnection() throws SQLException;

    public static DBFactory getDBFactory(String dbType) {
        if (dbType.equalsIgnoreCase("MYSQL")) {
            return new MySQLDBFactory();
        } else if (dbType.equalsIgnoreCase("H2")) {
            return new H2DBFactory();
        } else {
            return null;
        }
    }
}

