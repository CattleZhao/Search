package com.scorpion.searchEngine;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Scorpion on 2017/3/20.
 */
public class SearchEngine {
    public static void main(String[] args) {

        try {
            IndexManager.createIndex();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
