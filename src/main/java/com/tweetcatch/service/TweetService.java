/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.dao.TweetDao;
import com.tweetcatch.dao.TwitterAccountDao;
import com.tweetcatch.model.Tweet;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.view.util.BeanTableModel;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import twitter4j.MediaEntity;
import twitter4j.MediaEntity.Variant;
import twitter4j.Status;

/**
 *
 * @author krisnamourtscf
 */
public class TweetService {

    private TwitterManager twitterManager = new TwitterManager();
    private TweetDao tweetDao = new TweetDao();
    private TwitterAccountDao twitterAccountDao = new TwitterAccountDao();
    private TwitterAccount twitterAccount;

    public void setAccount(TwitterAccount twitterAccount) {
        this.twitterAccount = twitterAccount;
        twitterManager.connect(twitterAccount);
    }
            
    
    public List<TwitterAccount> getAccounts() {
        twitterAccountDao.beginTransaction();
        List<TwitterAccount> accounts = twitterAccountDao.findAll();
        twitterAccountDao.commitAndCloseTransaction();
        return accounts;
    }

    private Tweet newTweetSave(TwitterAccount twitterAccount, String text) {
        Tweet tweetSave = new Tweet();
        tweetSave.setCreateDate(LocalDateTime.now());
        tweetSave.setTweetText(text);
        tweetSave.setTwitterAccount(twitterAccount);
        tweetSave.setComments(Long.parseLong("0"));
        tweetSave.setHeart(Long.parseLong("0"));
        tweetSave.setShared(Long.parseLong("0"));
        return tweetSave;
    }

    public boolean sendSimpleTweet(String text, List<File> images) throws Exception {
        Tweet tweetSave = null;
        if (images == null) {
            tweetSave = sendTextTweet(text);
        } else {
            tweetSave = sendTweetStream(text, images);
        }

        //AuditRule.audit(tweetSave);
        tweetDao.beginTransaction();
        tweetDao.save(tweetSave);
        tweetDao.commitAndCloseTransaction();
        //repository.persist(tweetSave);

        return true;
    }

    private Tweet sendTextTweet(String text) throws Exception {
        Tweet tweetSave = newTweetSave(twitterAccount, text);

        //twitterAccount.connect();
        twitterManager.connect(twitterAccount);
        Long id = twitterManager.tweet(text);//twitterAccount.tweet(text);

        tweetSave.setTweetId(id);
        tweetSave.setSaveDate(LocalDateTime.now());

        return tweetSave;
    }

//https://stackoverflow.com/questions/28394362/how-to-post-multiple-photo-in-single-tweet-via-twitter4j/44194428#44194428
    private Tweet sendTweetStream(String text, List<File> images) throws Exception {
        Tweet tweetSave = newTweetSave(twitterAccount, text);

        //twitterAccount.connect();
        twitterManager.connect(twitterAccount);

        Long id = twitterManager.tweetWithStream(text, images);//twitterAccount.tweetWithStream(text, images);

        tweetSave.setTweetId(id);
        tweetSave.setHasStream(true);
        tweetSave.setSaveDate(LocalDateTime.now());

        return tweetSave;
    }

    /*public List<Tweet> searchUserTweets(TwitterAccount account, String screenName, boolean downloadStream) throws Exception {
        twitterManager.connect(account);
        List<Tweet> tweets = new ArrayList<>();
        for (Status status : twitterManager.searchUserTweets(screenName)) {
            if (downloadStream) {
                download(account.getProfileName(), status);
            }
            tweets.add(new Tweet(account, status));
        }
        return tweets;
    }*/

    private void download(String directoryName, Status status) {

        MediaEntity[] medias = status.getMediaEntities();
        System.out.println("Total video " + medias.length);

        if (medias.length > 0) {
            File directory = createDirectoryIfNotExists(directoryName);
            for (MediaEntity m : medias) {
                try {
                    /*System.out.println(m.getURL());
                    System.out.println(m.getMediaURL());
                    System.out.println(m.getMediaURLHttps());
                    System.out.println(m.getExpandedURL());
                    System.out.println(m.getType());*/
                    URL url = new URL(m.getMediaURL());
                    if (m.getVideoVariants().length > 0) {//is video
                        for (Variant v : m.getVideoVariants()) {
                            url = new URL(v.getUrl());
                        }
                    }

                    for (Variant v : m.getVideoVariants()) {
                        System.out.println("V: " + v.getUrl());
                        System.out.println("V: " + v.getContentType());
                    }

                    //URL url = new URL(m.getMediaURL());
                    InputStream in = new BufferedInputStream(url.openStream());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int n = 0;
                    while (-1 != (n = in.read(buf))) {
                        out.write(buf, 0, n);
                    }
                    out.close();
                    in.close();
                    byte[] response = out.toByteArray();
                    FileOutputStream fos = new FileOutputStream(directory.getAbsolutePath() + "\\" + m.getId() + "." + getExtension(m.getType()));
                    fos.write(response);
                    fos.close();
                } catch (Exception ex) {
                    System.err.println("Não consegui baixar a imagem");
                    ex.printStackTrace();
                }
            }
        }
    }

    public JScrollPane getJTable(List lista) {
        Tabela tabela = new Tabela(lista);
        tabela.setFillsViewportHeight(true);
        //tabela.addMouseListener(new EventoMouse());
        JScrollPane scollPane = new JScrollPane(tabela);

        return scollPane;
    }

    class Tabela extends JTable {

        public Tabela(List<Tweet> modelos) {

            BeanTableModel<Tweet> tabela = new BeanTableModel<Tweet>(Tweet.class);
            tabela.addColumn("Usuário", "userName");
            tabela.addColumn("Texto", "tweetText");
            tabela.addColumn("TemStream", "hasStream");

            for (Tweet m : modelos) {
                tabela.addRow(m);
            }
            setModel(tabela);

        }
    }

    private File createDirectoryIfNotExists(String directory) {
        File file = new File(directory);
        if (file.exists()) {
            return file;
        } else {
            file.mkdir();
            return file;
        }
    }

    private String getExtension(String type) {
        if (type.equals("photo")) {
            return "jpg";
        } else if (type.equals("video")) {
            return "mp4";
        } else if (type.equals("animated_gif")) {
            return "gif";
        } else {
            return "err";
        }
    }

}
