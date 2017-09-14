/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author krismorte
 */
@Entity
@Table(name = "retweet")
public class ReTweet extends Status {

    @OneToOne
    private TwitterUser twitterUser;
    @Transient
    private Boolean makeRetweet;
    @Transient
    private twitter4j.Status status;

    public ReTweet(TwitterAccount twitterAccount, TwitterUser twitterUser, twitter4j.Status status) {
        setTweetId(status.getId());
        this.status = status;
        LocalDateTime date = status.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        setCreateDate(date);
        setUserName(twitterAccount.getProfileName());
        setTwitterAccount(twitterAccount);
        setHeart(new Long(status.getFavoriteCount()));
        setShared(new Long(status.getRetweetCount()));
        setTweetText(status.getText());
        setTwitterUser(twitterUser);
        setMakeRetweet(false);
        if (status.getMediaEntities().length > 0) {
            setHasStream(Boolean.TRUE);
        }
        if (status.getGeoLocation() != null) {
            setHasGeolocation(Boolean.TRUE);
        }
    }

    /**
     * @return the twitterUser
     */
    public TwitterUser getTwitterUser() {
        return twitterUser;
    }

    /**
     * @param twitterUser the twitterUser to set
     */
    public void setTwitterUser(TwitterUser twitterUser) {
        this.twitterUser = twitterUser;
    }

    /**
     * @return the makeRetweet
     */
    public Boolean getMakeRetweet() {
        return makeRetweet;
    }

    /**
     * @param makeRetweet the makeRetweet to set
     */
    public void setMakeRetweet(Boolean makeRetweet) {
        this.makeRetweet = makeRetweet;
    }

    /**
     * @return the status
     */
    public twitter4j.Status getStatus() {
        return status;
    }

}
