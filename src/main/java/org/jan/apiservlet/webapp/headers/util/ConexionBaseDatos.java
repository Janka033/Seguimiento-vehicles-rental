package org.jan.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBaseDatos {
    private static String url = "jdbc:mysql://localhost:3306/productoss";
    private static String username = "root";
    private static String password = "janka";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,username,password);
    }
}
