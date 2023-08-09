package com.munoz.act3.service;

import com.munoz.act3.beans.*;

import java.sql.*;

public class JDBCConnection {

    static Connection conn;

    public JDBCConnection() {

    }

    public static Connection connectToDatabase() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException exc) {
            throw new RuntimeException(exc);
        }
        String url1 = "jdbc:mysql://localhost:3306/design_patterns";
        String user = "root";
        String password = "root";

        try {
            conn = DriverManager.getConnection(url1, user, password);
            if (conn != null) {
                System.out.println("Connected to the database");
                return conn;
            }
        } catch (SQLException exc) {
            throw new RuntimeException(exc);
        }
        return null;
    }

    public boolean saveNewAlbum(Album album) {
        conn = connectToDatabase();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into album values (" + album.getTitle() + album.getArtist() + album.getYear() + album.getTracks());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}