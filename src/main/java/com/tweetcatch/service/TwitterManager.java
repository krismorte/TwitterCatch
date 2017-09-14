/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterAccountAccess;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import twitter4j.IDs;
import twitter4j.Query;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.UploadedMedia;
import twitter4j.User;
import twitter4j.api.HelpResources;

/**
 *
 * @author c007329
 */
public class TwitterManager {

    private Twitter twitter;

    public List<HelpResources.Language> getLanguages() throws Exception {
        return twitter.getLanguages();
    }

    public void connect(TwitterAccount account) {
        twitter = (Twitter) account.getAccountAccess().getConnection();
    }

    public void connect(TwitterAccountAccess account) {
        twitter = (Twitter) account.getConnection();
    }

    public Long tweet(String text) throws Exception {
        Status newStatus = twitter.updateStatus(text);
        newStatus.getFavoriteCount();
        newStatus.getUserMentionEntities();
        newStatus.getGeoLocation();
        return newStatus.getId();
    }

    public Long tweetWithStream(String text, List<File> images) throws Exception {
        long[] mediaIds = new long[images.size()];
        int contador = 0;

        for (File image : images) {
            UploadedMedia media = twitter.uploadMedia(image);
            mediaIds[contador] = media.getMediaId();
            contador++;
        }

        StatusUpdate tweet = new StatusUpdate(text);
        tweet.setMediaIds(mediaIds);
        Status newStatus = twitter.updateStatus(tweet);
        return newStatus.getId();
    }

    public void reTweet(Status status) throws Exception {
        twitter.retweetStatus(status.getId());
    }

    public List<Status> searchUserTweets(String name, String language) throws Exception {
        Query query = new Query("from:" + name);
        query.setLang(language);
        return twitter.search(query).getTweets();
    }

    public List<Status> searchUserTweets(String name, String queryString, String language) throws Exception {
        Query query = new Query(queryString + " from:" + name);
        query.setLang(language);
        return twitter.search(query).getTweets();
    }

    public List<Status> searchPublicTweets(String queryString, String language) throws Exception {
        Query query = new Query(queryString);
        query.setLang(language);
        return twitter.search(query).getTweets();
    }

    public User extractUser() throws Exception {
        if (twitter != null) {
        }
        return twitter.showUser(twitter.getScreenName());
    }

    public User getUser(String screenName) throws Exception {
        return twitter.showUser(screenName);
    }

    public User getUser(long id) throws Exception {
        return twitter.showUser(id);
    }

    public IDs getFriendsIDs(long cursor) throws Exception {
        return twitter.getFriendsIDs(twitter.getScreenName(), cursor);
    }

    public IDs getFollowersIDs(long cursor) throws Exception {
        return twitter.getFollowersIDs(twitter.getScreenName(), cursor);
    }

    public IDs getFriendsIdsByUser(User user, long cursor) throws Exception {
        return twitter.getFriendsIDs(user.getScreenName(), cursor);
    }

    public IDs getFriendsIdsByUser(String profileName, long cursor) throws Exception {
        return twitter.getFriendsIDs(profileName, cursor);
    }

    public IDs getFollowersIdsByUser(User user, long cursor) throws Exception {
        return twitter.getFollowersIDs(user.getScreenName(), cursor);
    }

    public IDs getFollowersIdsByUser(String profileName, long cursor) throws Exception {
        return twitter.getFollowersIDs(profileName, cursor);
    }

}
