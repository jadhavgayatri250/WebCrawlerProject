package com.crawler.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void createTable() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            // Crawled URLs Table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS crawled_urls(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "url TEXT UNIQUE," +
                            "status TEXT DEFAULT 'VISITED'" +
                            ")"
            );

            // Users Table
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS users(" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "username TEXT UNIQUE," +
                            "password TEXT" +
                            ")"
            );

            System.out.println(
                    "Tables Created Successfully"
            );

            con.close();

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }
    }
}