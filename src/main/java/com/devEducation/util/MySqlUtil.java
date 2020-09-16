package com.devEducation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlUtil {

    private static final String USER = "root";
    private static final String PASS = "root";

    private static final String URL_MYSQL =
            "jdbc:mysql://34.67.123.43:3306?useSSL=false&useUnicode=true&serverTimezone=UTC";

    public static Connection getDBConnection() {
        try {
//            Class.forName("org.mysql.Driver");
            return DriverManager.getConnection(URL_MYSQL, USER,PASS );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
