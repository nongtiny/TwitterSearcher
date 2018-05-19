/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author Administrator
 */
public class SetupForWord {

    private Connection conn;
    private ResultSet results;
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
            String line = "-------------------------------------------------------------";
            while (results.next()) {
                user = results.getString(1);
                data = results.getString(2);
                if (searchWord.containsKey((String) str) == true
                        && !searchWord.get(str).equals(data)) {
                    StringBuilder sb = new StringBuilder(searchWord.get(str));
                    sb.append("[User: ").append(user).append("]\n    ").append(data).append("\n\n\n");

                    searchWord.put(str, sb.toString());
                } else {
                    searchWord.put(str, "[User: "+user+"]\n"+"    "+data+"\n\n\n");
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
