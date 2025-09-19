package com.fundacionfomento.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConfig {

    // Metodo que se encarga de generar una conexion entre mi  aplicacion y el servidor de base de datos
    public Connection getConnection() {
        try {
            String host = "jdbc:mysql://localhost:3306";
            String user = "root";
            String password = "root";
            String database = "fundacionfomentodb";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return DriverManager.getConnection(host + "/" + database + "?user=" + user + "&" + "password=" + password);

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }


}