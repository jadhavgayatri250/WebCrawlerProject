package com.crawler.dao;

import com.crawler.database.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StatisticsDAO {

    public void showStatistics() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT COUNT(*) FROM crawled_urls"
                    );

            if(rs.next()) {

                System.out.println(
                        "Total URLs in Database : "
                                + rs.getInt(1)
                );
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
