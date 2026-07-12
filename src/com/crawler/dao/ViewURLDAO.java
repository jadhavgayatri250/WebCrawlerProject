package com.crawler.dao;

import com.crawler.database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewURLDAO {

    public void showAllURLs() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM crawled_urls"
                    );

            System.out.println("\n===== SAVED URLS =====");

            while(rs.next()) {

                System.out.println(
                        rs.getInt("id")
                                + " | "
                                + rs.getString("url")
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}