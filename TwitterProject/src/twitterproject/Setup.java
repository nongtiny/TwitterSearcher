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
import javax.swing.JTextArea;
import static twitterproject.LoadingScreen.jTextArea1;

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
        StringBuilder sb = new StringBuilder("Welcome to noob twitter searcher");
        sb.append("\n\n" + "Step 1: Setting user tweets");
        jTextArea1.setText(sb.toString());
        setUser.set(reader);
        sb.append("\n Finished\n\n");
        sb.append("Step 2: Setting hashtag tweets");
        jTextArea1.setText(sb.toString());
        //setHashtag.set();
        sb.append("\n Finished");
        jTextArea1.setText(sb.toString());
        
    }
    
    
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
