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
import java.util.List;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public class SetupForUsername {
    private ResultSet results;
    private Statement stmnt;
    private Connection conn;
    private List<String> userName;
    private Map<String, Integer> userPostCount;
    private Map<String, String> userPost;
    
    public SetupForUsername() throws ClassNotFoundException{
        String sql = "SELECT screen_name,text_tweet FROM TWITTER_TWEET";
        try {
            conn = TwitterProject.getConnect();
            stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            while (results.next()) {
                String user = results.getString(1);
                String tweet = results.getString(2);
                userName.add(user);
                if (userPost.containsKey((String) user) == true
                            && !userPost.get(user).equals(tweet)) {
                        StringBuilder sb = new StringBuilder(userPost.get(tweet));
                        sb.append("\n\n\n"+tweet);
                        int j = 0;
                        while ((j = sb.indexOf(" ", j + 60)) != -1) {
                            sb.replace(j, j + 1, "\n");
                        }
                        userPost.put(user, sb.toString());
                    } else {
                        userPost.put(user, tweet);
                    }
                
            }

            for (String tmp : userName) {
                Integer count = userPostCount.get(tmp);
                userPostCount.put(tmp, (count == null) ? 1 : count.intValue() + 1);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
