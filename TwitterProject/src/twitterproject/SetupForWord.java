/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import com.univocity.parsers.csv.CsvParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SetupForWord {

    Connection conn;
    ResultSet results;
    String path;
    private Statement stmnt;
    private Map<String, String> searchWord;

    public SetupForWord(Connection conn){
        this.conn = conn;
        searchWord = new HashMap<String,String>();
    }

    public void set(String str) {
        String sql = "SELECT screen_name, text_tweet FROM TWITTER_TWEET WHERE text_tweet LIKE '%" + str + "%'";
        try {
            stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            String user;
            String data;

            while (results.next()) {
                user = results.getString(1);
                data = results.getString(2);
                if (searchWord.containsKey((String) str) == true
                        && !searchWord.get(str).equals(data)) {
                    StringBuilder sb = new StringBuilder(searchWord.get(str));
                    sb.append(String.format("[User: %s]\n    %s\n\n\n", user, data));

                    searchWord.put(str, sb.toString());
                } else {
                    searchWord.put(str, String.format("[User: %s]\n    %s\n\n\n", user, data));
                }
            }
            System.out.println("Word finish");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, String> getSearchWord() {
        return searchWord;
    }
    
    

}
