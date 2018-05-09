/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Filesearch {

    BufferedReader br;
    FileReader fr;
    ResultSet results;
    Connection con;
    
    public Filesearch() {

    }

    public Filesearch(FileReader file) {
        con   = TwitterProject.getConnect();
        fr = file;
    }

    public void searchFile(String searchStr) {
        try {
            br = new BufferedReader(fr);
            String line = br.readLine().toLowerCase();
            while (line != null) {
                if (isContainExactWord(line.toLowerCase(), searchStr.toLowerCase())) {
                    addWord(searchStr, line);
                    System.out.println(line);
                }
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace(); 
            }
        }
    }

    public void addWord(String word, String line) {
        String sql = "insert into TWEET.SEARCHTWEET values ('" + word + "', '" + line + "')";
        try {
            Statement stm = con.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isContainExactWord(String fullString, String partWord) {
        String pattern = "\\b" + partWord + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(fullString);
        return m.find();
    }
}
