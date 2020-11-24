package com.devEducation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlUtil {

    private static final String USER = "root";
    private static final String PASS = "bv8f58w2";

    private static final String URL_MYSQL =
            "jdbc:mysql://34.121.230.88:3306?useSSL=false&useUnicode=true&serverTimezone=UTC";

    public static Connection getDBConnection() {
        try {
            //Class.forName("org.mysql.Driver");
            return DriverManager.getConnection(URL_MYSQL, USER,PASS );
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return null;
    }


}
