package fr.hiit.pretapreter.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class DataBaseManager {
    // Chemin vers la base de données H2 (fichier local)
    // url a rentré dans le local host : jdbc:h2:mem:empruntsdb
    private static final String DB_URL = "jdbc:h2:mem:empruntsdb;DB_CLOSE_DELAY=-1;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASS = "admin";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }





}
