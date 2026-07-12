package com.crawler.dao;

import com.crawler.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class URLDAO {

    public void saveURL(String url) {

        synchronized (URLDAO.class) {

            try (Connection con = DBConnection.getConnection()) {

                String checkSql =
                        "SELECT id FROM crawled_urls WHERE url=?";

                PreparedStatement checkPs =
                        con.prepareStatement(checkSql);

                checkPs.setString(1, url);

                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {
                    return;
                }

                String sql =
                        "INSERT INTO crawled_urls(url) VALUES(?)";

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ps.setString(1, url);

                ps.executeUpdate();

                System.out.println("Saved : " + url);

            } catch (Exception e) {

                // Duplicate silently ignore

            }
        }
    }
}