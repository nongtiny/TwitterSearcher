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
import static twitterproject.LoadingScreen.jTextArea1;

/**
 *
 * @author Administrator
 */
public class Setup {
    
    private Connection conn;
    private SetupForHashtag setHashtag;
    private SetupForUsername setUser;
    private SetupForWord setWords;
    private CsvParser reader;
    private CsvParserSettings parserSettings;

    public Setup() throws ClassNotFoundException {
        conn = TwitterProject.getConnect();

        parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setCharToEscapeQuoteEscaping('\\');
        reader = new CsvParser(parserSettings);
        reader.beginParsing(new File(Setup.class.getResource("twitter_tweet.csv").toString().substring(6)));
        setUser = new SetupForUsername();
        setHashtag = new SetupForHashtag(conn);
        setWords = new SetupForWord(conn);
        StringBuilder sb = new StringBuilder("Welcome to noob twitter searcher");
        sb.append("\n\n" + "Step 1: Setting user tweets");
        jTextArea1.setText(sb.toString());
        setUser.set(reader);
        sb.append("  [Finished]\n\n");
        sb.append("Step 2: Setting hashtag tweets");
        jTextArea1.setText(sb.toString());
        setHashtag.set();
        sb.append("  [Finished]");
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
