/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Administrator
 */
public class TwitterProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
       LoadingScreen load = new LoadingScreen();
       load.setVisible(true);     
       Homepage home = new Homepage();
       load.dispose();
       home.setVisible(true);
    }

    public static Connection getConnect() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://mysql.ilab.sit.kmutt.ac.th/twitter_data_tinny", "tinny", ".=hxitdv[vk=urwfh");
            System.out.println("Connect successful ! ");
            return conn;
            //conn.close();
        } catch (SQLException ex) {
            System.out.println("Connect failed !!! ");
            ex.printStackTrace();
        }
        return null;

    }

}
