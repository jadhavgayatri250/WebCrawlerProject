package com.crawler.dao;

import com.crawler.database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    // Register User
    public boolean registerUser(String username, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String check =
                    "SELECT * FROM users WHERE username=?";

            PreparedStatement ps1 =
                    con.prepareStatement(check);

            ps1.setString(1, username);

            ResultSet rs =
                    ps1.executeQuery();

            if (rs.next()) {

                con.close();
                return false;
            }

            String sql =
                    "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement ps2 =
                    con.prepareStatement(sql);

            ps2.setString(1, username);
            ps2.setString(2, password);

            ps2.executeUpdate();

            con.close();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // Login User
    public boolean loginUser(String username, String password) {

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

            boolean success =
                    rs.next();

            con.close();

            return success;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}