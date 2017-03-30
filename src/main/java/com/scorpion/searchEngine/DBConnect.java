package com.scorpion.searchEngine;

import com.scorpion.pojo.New;
import com.scorpion.pojo.NewWithBLOBs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Scorpion on 2017/3/20.
 */
public class DBConnect {
    private Connection conn = null;
    private PreparedStatement pst = null;
    private final String url = "jdbc:mysql://127.0.0.1/testdb?useUnicode=true&characterEncoding=UTF-8";
    private final String name = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "123456";

    public DBConnect() {
        init();
    }

    private void init() {
        try {
            Class.forName(name);
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void prepareStatement(String sql) throws SQLException {
        pst = conn.prepareStatement(sql);
    }

    public ResultSet executeQuery() throws SQLException {
        if(pst != null) {
            return pst.executeQuery();
        }
        return null;
    }

    public void executeUpdate() throws SQLException {
        if(pst != null) {
            pst.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if(pst != null) {
            pst.close();
            pst = null;
        }

        if(conn != null) {
            conn.close();
            conn = null;
        }
    }

    public void setString(int index, String value) throws SQLException {
        pst.setString(index, value);
    }

    public List<NewWithBLOBs> getDatas() throws SQLException {
        String sql = "SELECT * FROM topcompany";
        List<NewWithBLOBs> resultList = new ArrayList<>();
        this.prepareStatement(sql);
        ResultSet result = this.executeQuery();
        while(result.next()) {
            NewWithBLOBs data = new NewWithBLOBs();
            data.setName(result.getString("name"));
            data.setTitle(result.getString("title"));
            data.setUrl(result.getString("url"));
            data.setDate(result.getString("date"));
            data.setDescription(result.getString("description"));
            data.setHtml(result.getString("html"));
            data.setCategory(result.getString("category"));
            data.setSource(result.getString("source"));
            data.setKeywords(result.getString("keywords"));
            resultList.add(data);
        }
        this.close();
        return resultList;
    }


    public boolean isExist(String url) throws SQLException {
        String sql = "SELECT id FROM topcompany WHERE url = ?";
        this.prepareStatement(sql);
        this.setString(1, url);
        ResultSet rs = this.executeQuery();
        int id = -1;
        while(rs.next()) {
            id = rs.getInt("id");
        }
        return id <= 0 ? false : true;
    }
}
