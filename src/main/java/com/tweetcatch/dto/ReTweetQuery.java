/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dto;

/**
 *
 * @author krismorte
 */
public class ReTweetQuery {

    public boolean publicTweet;
    public boolean myTweet;
    public boolean specificTweet;
    public boolean withStream;
    public boolean withLanguage;
    public boolean downloadStream;

    public String profile;
    public String language;
    public String query;

    public ReTweetQuery(boolean publicTweet, boolean myTweet, boolean specificTweet, boolean withStream,boolean withLanguage, boolean downloadStream, String profile, String language, String query) {
        this.publicTweet = publicTweet;
        this.myTweet = myTweet;
        this.specificTweet = specificTweet;
        this.withStream = withStream;
        this.withLanguage = withLanguage;

        this.downloadStream = downloadStream;
        this.profile = profile;
        this.language = language;
        this.query = query;
        if (this.withLanguage == false) {
            this.language = "";
        }
    }

}
