/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Administrator
 */
public class SetupForUsername {
    
	
    //For search user
    public List<String> userName;
    public Map<String, Integer> userPostCount;
    public Map<String, String> userPost;

 

    public SetupForUsername() {
        userName = new ArrayList<String>();
        userPost = new HashMap<String, String>();
        userPostCount = new HashMap<String, Integer>();
    }
    
    public void set(CsvParser reader){
         String[] nextLine;

        while ((nextLine = reader.parseNext()) != null) {
            String user = nextLine[1];
            String tweet = nextLine[2];
            userName.add(user);
            if (userPost.containsKey((String) user) == true
                    && !userPost.get(user).equals(tweet)) {
                StringBuilder sb = new StringBuilder(userPost.get(user));
                sb.append(String.format("\n\n\n  %s", tweet));

                userPost.put(user, sb.toString());
            } else {
                userPost.put(user, String.format("   %s", tweet));
            }

        }
        for (String tmp : userName) {
            Integer count = userPostCount.get(tmp);
            userPostCount.put(tmp, (count == null) ? 1 : count.intValue() + 1);
        }
        reader.stopParsing();
        System.out.println("User name finish");
    }
    
     public List<String> getUserName() {
        return userName;
    }

    public Map<String, String> getUserPost() {
        return userPost;
    }

    public Map<String, Integer> getUserPostCount() {
        return userPostCount;
    }

}
