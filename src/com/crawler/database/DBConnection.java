package com.crawler.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
            "jdbc:sqlite:crawler.db";

    public static Connection getConnection() {

        try {

            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(URL);

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
    }
}