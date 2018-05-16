/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterproject;

import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static twitterproject.LoadingScreen.jLabel3;

/**
 *
 * @author Administrator
 */
public class Setup {
    private ResultSet results;
    private Statement stmnt;
    private Connection conn;
    private List<String> hashtagWord;
    private Map<String, Integer> hashtagCount;
    private Map<String, String> hashtagResult;
    private SetupForHashtag setHashtag;
    private SetupForUsername setUser;
    private SetupForWord setWords;
    private ValueComparator vc;
    private CsvParser reader;
    private CsvParserSettings parserSettings;
    public String status;
    
    Map<String, Integer> sortedTopHashtag;
    Map<String, Integer> sortedTopTweet;

    public Setup() throws ClassNotFoundException {
        //conn = TwitterProject.getConnect();

        hashtagWord = new ArrayList<String>();
        hashtagCount = new HashMap<String, Integer>();
        hashtagResult = new HashMap<String, String>();
        parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setCharToEscapeQuoteEscaping('\\');
        reader = new CsvParser(parserSettings);
        reader.beginParsing(new File(Setup.class.getResource("twitter_tweet.csv").toString().substring(6)));
        setUser = new SetupForUsername();
        //setHashtag = new SetupForHashtag(conn);
        setWords = new SetupForWord(conn);
        status = "Setting user tweets";
        jLabel3.setText(status);
        setUser.set(reader);
        status = "Setting hashtag tweets";
        jLabel3.setText(status);
        //setHashtag.set();
        jLabel3.setText("Finish");
        
    }
    
//    public void set(){
//        status = "Setting user tweets";
//        jLabel3.setText(status);
//        setUser.set(reader);
//        status = "Setting hashtag tweets";
//        jLabel3.setText(status);
//        //setHashtag.set();
//    }
    
    public SetupForHashtag getSetHashtag() {
        return setHashtag;
    }

    public SetupForUsername getSetUser() {
        return setUser;
    }

    public SetupForWord getSetWords() {
        return setWords;
    }
    
}
