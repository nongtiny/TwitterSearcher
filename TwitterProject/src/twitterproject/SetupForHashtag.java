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
import java.util.*;

/**
 *
 * @author Administrator
 */
public class SetupForHashtag {
     private ResultSet results;
     private Statement stmnt;
    private Connection conn;
    private List<String> hashtagWord;
    private Map<String, Integer> hashtagCount;
    private Map<String, String> hashtagResult;
    
    public SetupForHashtag(Connection conn) {
        this.conn = conn;
        hashtagWord = new ArrayList<String>();
        hashtagCount = new HashMap<String, Integer>();
        hashtagResult = new HashMap<String, String>();
    }

    void set()  {
        String sql = "SELECT text_tweet FROM TWITTER_TWEET WHERE text_tweet LIKE '%#%'";
       try{
           stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            String data;
            String[] hashtag;
            while (results.next()) {
                data = results.getString(1);
                hashtag = data.split("#");
                for (int i = 1; i < hashtag.length; i++) {
                    if (hashtag[i].equals("")) {
                    } else {
                        hashtagWord.add("#" + hashtag[i]);
                    }
                    if (hashtagResult.containsKey((String) hashtag[i]) == true
                            && !hashtagResult.get(hashtag[i]).equals(data)) {
                        StringBuilder sb = new StringBuilder(hashtagResult.get(hashtag[i]));
                        sb.append("\n\n\n" + data);
                        hashtagResult.put(hashtag[i], sb.toString());
                    } else {
                        hashtagResult.put(hashtag[i], data);
                    }
                }
            }
            
       

            for (String tmp : hashtagWord) {
                Integer count = hashtagCount.get(tmp);
                hashtagCount.put(tmp, (count == null) ? 1 : count.intValue() + 1);
            }
            System.out.println("Hastag finish");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public Map<String, String> getHashtagResult() {
        return hashtagResult;
    }

    public Map<String, Integer> getHashtagCount() {
        return hashtagCount;
    }
    
    
    public List<String> getHashtagWord() {
        return hashtagWord;
    }

}
