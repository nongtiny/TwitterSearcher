/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Administrator
 */
public class TwitterProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{
          Filesearch file = new Filesearch(new FileReader("D:\\เรียน\\Sophomore\\Term2\\oosd\\ProjectReal\\test.txt"));
          file.searchFile("again");
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	Calendar cal = Calendar.getInstance();
//	System.out.println((String)dateFormat.format(cal)); //2016/11/16 12:08:43
      
        

    }

    public static Connection getConnect() {
        String connectionURL = "jdbc:derby://localhost:1527/Twitter";
        try {
            Connection conn = DriverManager.getConnection(connectionURL, "tweet", "1234");
            System.out.println("Connect successful ! ");
            return conn;
            //conn.close();
        } catch (SQLException ex) {
            System.out.println("Connect failed ! ");
        }
        return null;
    }

}
