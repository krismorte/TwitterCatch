/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.dao.ReTweetDao;
import com.tweetcatch.dao.TwitterAccountDao;
import com.tweetcatch.dao.TwitterUserDao;
import com.tweetcatch.dto.ReTweetQuery;
import com.tweetcatch.model.ReTweet;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterUser;
import com.tweetcatch.view.swing.MyJTable;
import com.tweetcatch.view.util.BeanTableModel;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import twitter4j.MediaEntity;
import twitter4j.MediaEntity.Variant;
import twitter4j.Status;
import twitter4j.api.HelpResources;

/**
 *
 * @author krismorte
 */
public class ReTweetService {

    private TwitterManager twitterManager = new TwitterManager();
    private TwitterAccountDao twitterAccountDao = new TwitterAccountDao();
    private TwitterUserDao twitterUserDao = new TwitterUserDao();
    private ReTweetDao reTweetDao = new ReTweetDao();
    private TwitterAccount account;

    public void setAccount(TwitterAccount account) {
        this.account = account;
        twitterManager.connect(account);
    }

    public List<TwitterAccount> getAccounts() {
        twitterAccountDao.beginTransaction();
        List<TwitterAccount> accounts = twitterAccountDao.findAll();
        twitterAccountDao.commitAndCloseTransaction();
        return accounts;
    }

    public List<TwitterUser> getUsers() {
        twitterUserDao.beginTransaction();
        List<TwitterUser> accounts = twitterUserDao.listByAccount(account);
        twitterUserDao.commitAndCloseTransaction();
        return accounts;
    }

    public List<HelpResources.Language> getLanguages() throws Exception {
        return twitterManager.getLanguages();
    }

    public void makeReTweets(List<ReTweet> reTweets) throws Exception {

        for (ReTweet reTweet : reTweets) {
            twitterUserDao.beginTransaction();
            TwitterUser twitterUser = twitterUserDao.getByProfileId(reTweet.getTwitterUser().getProfileId());

            if (twitterUser == null) {
                twitterUser = reTweet.getTwitterUser();
                twitterUserDao.save(twitterUser);
            }
            reTweet.setTwitterUser(twitterUser);
            twitterUserDao.commitAndCloseTransaction();

            twitterManager.reTweet(reTweet.getStatus());

            reTweetDao.beginTransaction();
            reTweetDao.save(reTweet);
            reTweetDao.commitAndCloseTransaction();
        }

    }

    public List<ReTweet> searhTweets(ReTweetQuery reTweetQuery) throws Exception {
        List<ReTweet> reTweets = null;

        if (reTweetQuery.publicTweet) {
            reTweets = getReTweets(twitterManager.searchPublicTweets(reTweetQuery.query, reTweetQuery.language), reTweetQuery.withStream, reTweetQuery.downloadStream);
        } else if (reTweetQuery.myTweet) {
            reTweets = getReTweets(twitterManager.searchUserTweets(account.getProfileName(), reTweetQuery.query, reTweetQuery.language), reTweetQuery.withStream, reTweetQuery.downloadStream);
        } else if (reTweetQuery.specificTweet) {
            reTweets = getReTweets(twitterManager.searchUserTweets(reTweetQuery.profile, reTweetQuery.query, reTweetQuery.language), reTweetQuery.withStream, reTweetQuery.downloadStream);
        }

        return reTweets;
    }

    private List<ReTweet> getReTweets(List<Status> statuses, boolean withStream, boolean downloadStream) throws Exception {
        List<ReTweet> reTweets = new ArrayList<>();
        for (Status status : statuses) {
            if (withStream) {
                if (status.getMediaEntities().length > 0) {
                    reTweets.add(new ReTweet(account, new TwitterUser(account, status.getUser()), status));
                }
            } else {
                reTweets.add(new ReTweet(account, new TwitterUser(account, status.getUser()), status));
            }
            if (downloadStream) {
                download(account.getProfileName(), status);
            }

        }
        return reTweets;
    }

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

    public MyJTable getJTable(List<ReTweet> lista) {

        BeanTableModel<ReTweet> modelo = new BeanTableModel<ReTweet>(ReTweet.class);
        modelo.addColumn("Realizar", "makeRetweet", BeanTableModel.EditMode.EDITABLE);
        modelo.addColumn("Data Criação", "createDate");
        modelo.addColumn("Usuário", "twitterUser");
        modelo.addColumn("Texto", "tweetText");
        modelo.addColumn("Curtidas", "heart");
        modelo.addColumn("Compartilhamento", "shared");
        modelo.addColumn("Anexo", "hasStream");

        for (ReTweet m : lista) {
            modelo.addRow(m);
        }

        MyJTable tabela = new MyJTable(modelo);

        return tabela;
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
