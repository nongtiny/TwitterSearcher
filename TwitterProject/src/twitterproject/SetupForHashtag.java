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

    public SetupForHashtag() throws ClassNotFoundException {
        hashtagWord = new ArrayList<String>();
        hashtagCount = new HashMap<String, Integer>();
        hashtagResult = new HashMap<String, String>();
        String sql = "SELECT text_tweet FROM TWITTER_TWEET WHERE text_tweet LIKE '%#'";
        try {
            conn = TwitterProject.getConnect();
            stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            while (results.next()) {
                String data = results.getString(1);
                String[] hashtag = data.split("#");
                for (int i = 1; i < hashtag.length; i++) {
                    if (hashtag[i].equals("")) {
                    } else {
                        hashtagWord.add("#" + hashtag[i]);
                    }
                    if (hashtagResult.containsKey((String) hashtag[i]) == true
                            && !hashtagResult.get(hashtag[i]).equals(data)) {
                        StringBuilder sb = new StringBuilder(hashtagResult.get(hashtag[i]));
                        sb.append("\n\n\n" + data);
                        int j = 0;
                        while ((j = sb.indexOf(" ", j + 60)) != -1) {
                            sb.replace(j, j + 1, "\n");
                        }
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

}
