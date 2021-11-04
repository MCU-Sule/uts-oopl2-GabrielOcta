package com.example.squiddemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Author 1972037 Gabriel Octa Mahardika
 **/
public class JDBCConnection {
    public static Connection getConnection (){
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/squiddb",
                    "root",
                    "12345"
            );
        }
        catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return connection;
    }
}
