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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class Homepage extends javax.swing.JFrame {

    /**
     * Creates new form Homepage
     */
    private ResultSet results;
    private Statement stmnt;
    private Connection conn;
    public SetupForHashtag set;
    Map<String, Integer> sortedMap;

    public Homepage() throws ClassNotFoundException {
        set = new SetupForHashtag();
        initComponents();
        ValueComparator vc =  new ValueComparator(set.getHashtagCount());
        sortedMap = new TreeMap<String, Integer>(vc);
        sortedMap.putAll(set.getHashtagCount()); 
        int i = 0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            switch (i) {
                case 0:
                    top1Label.setText(entry.getKey());
                    top1Res.setText("" + entry.getValue()+" tweets");
                    break;
                case 1:
                    top2Label.setText(entry.getKey());
                    top2Res.setText("" + entry.getValue()+" tweets");
                    break;
                case 2:
                    top3Label.setText(entry.getKey());
                    top3Res.setText("" + entry.getValue()+" tweets");
                    break;
                case 3:
                    top4Label.setText(entry.getKey());
                    top4Res.setText("" + entry.getValue()+" tweets");
                    break;
                case 4:
                    top5Label.setText(entry.getKey());
                    top5Res.setText("" + entry.getValue()+" tweets");
                    break;
            }
            i++;
        }
    }

    public void searchTweetFromUsername(String user) {
        String sql = "SELECT text_tweet FROM TWITTER_TWEET WHERE "
                + "screen_name LIKE'%" + user + "%'";
        String tweets = "";
        int cnt = 1;
        try {

            stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            tweetsResult.setText("");
            if (results.next() != false) {
                while (results.next()) {
                    tweetsResult.append(String.format(" (%d)  %s\n", cnt, user));
                    tweets = String.format("\t%s", results.getString(1));
                    tweetsResult.append(tweets);
                    tweetsResult.append("\n");
                    cnt++;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Result not found", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Please insert the value", "Error!", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (Exception e) {
            this.dispose();
            e.printStackTrace();

        }

    }

    public void searchWordInTweet(String word) {
        String sql = "SELECT * FROM TWITTER_TWEET WHERE  "
                + "text_tweet LIKE '% " + word + " %'";
        int cnt = 1;
        try {
            stmnt = conn.createStatement();
            results = stmnt.executeQuery(sql);
            String userData, text;
            tweetsResult.setText("");
            while (results.next()) {
                userData = String.format("(%d)\t User: %s", cnt, results.getString(2));
                tweetsResult.append(userData + "\n");
                text = String.format("%50s", results.getString(3));
                tweetsResult.append(text);
                tweetsResult.append("\n");
                tweetsResult.append("----------------------------------------------------------------\n");
                cnt++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            this.dispose();
            e.printStackTrace();
        }
    }

    public void searchByHashtag(String hashtag) {
         tweetsResult.setText(set.getHashtagResult().get(hashtag));
    }
       
    

    public void search(String type, String word) throws ClassNotFoundException {
        tweetsResult.setText("");
        try {
            if (type.equalsIgnoreCase("Word")) {
                searchWordInTweet(word);
            } else if (type.equalsIgnoreCase("User")) {
                searchTweetFromUsername(word);
            } else if (type.equalsIgnoreCase("Hashtag")) {
                searchByHashtag(word);
            }
        } catch (NullPointerException n) {
            JOptionPane.showMessageDialog(null, "Please insert record", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void topHashtag() throws ClassNotFoundException {
//        String sql = "SELECT text_tweet FROM TWITTER_TWEET WHERE text_tweet LIKE '%#'";
//        hashtag = "";
//        cntHashtag = 0;
//        try {
//            conn = TwitterProject.getConnect();
//            stmnt = conn.createStatement();
//            results = stmnt.executeQuery(sql);
//            while (results.next()) {
//                hashtag = results.getString(1);
//                Pattern pattern = Pattern.compile("((?:\\S\\u0023)[\\u0E00-\\u0E60 0-9 a-z A-Z]+)");
//                Matcher matcherForHashtag = pattern.matcher(hashtag);
//                while (matcherForHashtag.find()) {
//                    //System.out.println(matcher.group(1));
//                    hashTagData.add(matcherForHashtag.group(1).substring(1));
//                }
//            }
//            
//            for(String tmp : hashTagData){
//                Integer count = hashTagCount.get(tmp);
//                hashTagCount.put(tmp, (count == null) ? 1 : count + 1);
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tweetsResult = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        top1Label = new javax.swing.JLabel();
        top1Res = new javax.swing.JLabel();
        top2Label = new javax.swing.JLabel();
        top2Res = new javax.swing.JLabel();
        top3Label = new javax.swing.JLabel();
        top3Res = new javax.swing.JLabel();
        top4Label = new javax.swing.JLabel();
        top4Res = new javax.swing.JLabel();
        top5Label = new javax.swing.JLabel();
        top5Res = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        top1Tweeter = new javax.swing.JLabel();
        top1TweeterRes = new javax.swing.JLabel();
        top2Tweeter = new javax.swing.JLabel();
        top2TweeterRes = new javax.swing.JLabel();
        top3Tweeter = new javax.swing.JLabel();
        top3TweeterRes = new javax.swing.JLabel();
        top4Tweeter = new javax.swing.JLabel();
        top4TweeterRes = new javax.swing.JLabel();
        top5Tweeter = new javax.swing.JLabel();
        top5TweeterRes = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1445, 850));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(239, 239, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1468, 753));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Twitter Searcher");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(392, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(427, 427, 427))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        tweetsResult.setColumns(20);
        tweetsResult.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tweetsResult.setLineWrap(true);
        tweetsResult.setRows(5);
        tweetsResult.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setViewportView(tweetsResult);

        jPanel3.setBackground(new java.awt.Color(203, 244, 239));

        searchButton.setBackground(new java.awt.Color(255, 255, 255));
        searchButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(51, 153, 255));
        searchButton.setText("Search");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchField.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        searchField.setForeground(new java.awt.Color(51, 153, 255));
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 255));
        jLabel2.setText("Search by");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 153, 255));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Word", "User", "Hashtag", " " }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField)
                    .addComponent(jLabel2)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Top 5 hashtag");

        top1Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top1Label.setForeground(new java.awt.Color(0, 153, 204));
        top1Label.setText("#");

        top1Res.setForeground(new java.awt.Color(204, 204, 204));
        top1Res.setText("-");

        top2Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top2Label.setForeground(new java.awt.Color(0, 153, 204));
        top2Label.setText("#");

        top2Res.setForeground(new java.awt.Color(204, 204, 204));
        top2Res.setText("-");

        top3Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top3Label.setForeground(new java.awt.Color(0, 153, 204));
        top3Label.setText("#");

        top3Res.setForeground(new java.awt.Color(204, 204, 204));
        top3Res.setText("-");

        top4Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top4Label.setForeground(new java.awt.Color(0, 153, 204));
        top4Label.setText("#");

        top4Res.setForeground(new java.awt.Color(204, 204, 204));
        top4Res.setText("-");

        top5Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top5Label.setForeground(new java.awt.Color(0, 153, 204));
        top5Label.setText("#");

        top5Res.setForeground(new java.awt.Color(204, 204, 204));
        top5Res.setText("-");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(top1Res)
                    .addComponent(top1Label)
                    .addComponent(top2Label)
                    .addComponent(top2Res)
                    .addComponent(top3Label)
                    .addComponent(top3Res)
                    .addComponent(top4Label)
                    .addComponent(top4Res)
                    .addComponent(top5Label)
                    .addComponent(top5Res))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(top1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top1Res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(top2Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top2Res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top3Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top3Res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top4Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top4Res)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top5Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top5Res)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Top 5 Tweeter");

        top1Tweeter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top1Tweeter.setForeground(new java.awt.Color(0, 153, 204));
        top1Tweeter.setText("*");

        top1TweeterRes.setForeground(new java.awt.Color(204, 204, 204));
        top1TweeterRes.setText("-");

        top2Tweeter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top2Tweeter.setForeground(new java.awt.Color(0, 153, 204));
        top2Tweeter.setText("*");

        top2TweeterRes.setForeground(new java.awt.Color(204, 204, 204));
        top2TweeterRes.setText("-");

        top3Tweeter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top3Tweeter.setForeground(new java.awt.Color(0, 153, 204));
        top3Tweeter.setText("*");

        top3TweeterRes.setForeground(new java.awt.Color(204, 204, 204));
        top3TweeterRes.setText("-");

        top4Tweeter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top4Tweeter.setForeground(new java.awt.Color(0, 153, 204));
        top4Tweeter.setText("*");

        top4TweeterRes.setForeground(new java.awt.Color(204, 204, 204));
        top4TweeterRes.setText("-");

        top5Tweeter.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        top5Tweeter.setForeground(new java.awt.Color(0, 153, 204));
        top5Tweeter.setText("*");

        top5TweeterRes.setForeground(new java.awt.Color(204, 204, 204));
        top5TweeterRes.setText("-");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel4))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(top1TweeterRes)
                            .addComponent(top1Tweeter)
                            .addComponent(top2Tweeter)
                            .addComponent(top2TweeterRes)
                            .addComponent(top3Tweeter)
                            .addComponent(top3TweeterRes)
                            .addComponent(top4Tweeter)
                            .addComponent(top4TweeterRes)
                            .addComponent(top5Tweeter)
                            .addComponent(top5TweeterRes))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(top1Tweeter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top1TweeterRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(top2Tweeter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top2TweeterRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top3Tweeter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top3TweeterRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top4Tweeter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top4TweeterRes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top5Tweeter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(top5TweeterRes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 141, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(519, 519, 519))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(158, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed


    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        try {
            search((String) jComboBox1.getSelectedItem(), searchField.getText());
            //topHashtag();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Homepage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Homepage().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Homepage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel top1Label;
    private javax.swing.JLabel top1Res;
    private javax.swing.JLabel top1Tweeter;
    private javax.swing.JLabel top1TweeterRes;
    private javax.swing.JLabel top2Label;
    private javax.swing.JLabel top2Res;
    private javax.swing.JLabel top2Tweeter;
    private javax.swing.JLabel top2TweeterRes;
    private javax.swing.JLabel top3Label;
    private javax.swing.JLabel top3Res;
    private javax.swing.JLabel top3Tweeter;
    private javax.swing.JLabel top3TweeterRes;
    private javax.swing.JLabel top4Label;
    private javax.swing.JLabel top4Res;
    private javax.swing.JLabel top4Tweeter;
    private javax.swing.JLabel top4TweeterRes;
    private javax.swing.JLabel top5Label;
    private javax.swing.JLabel top5Res;
    private javax.swing.JLabel top5Tweeter;
    private javax.swing.JLabel top5TweeterRes;
    private javax.swing.JTextArea tweetsResult;
    // End of variables declaration//GEN-END:variables
}
