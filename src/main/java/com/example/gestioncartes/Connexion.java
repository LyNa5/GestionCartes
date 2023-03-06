package com.example.gestioncartes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {
    static String user = "root";
    static String url = "jdbc:mysql://localhost:3306/gestioncartes?serverTimezone=UTC";
    static String pass = "";
    static String driver = "com.mysql.cj.jdbc.Driver";//"com.mysql.jdbc.Driver";
    //static Connection con;
    public static Connection getCon() {

        Connection con = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection (url, user, pass);
            System.out.println("Ok");
           // con = conn;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("bad");
            Logger.getLogger(Connexion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
