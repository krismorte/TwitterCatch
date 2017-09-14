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

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "tweet")
public class Tweet extends Status {
    
    public Tweet() {
    }
    
    public Tweet(TwitterAccount twitterAccount, twitter4j.Status status) {
        setTweetId(status.getId());
        LocalDateTime date = status.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        
        setCreateDate(date);
        setUserName(twitterAccount.getProfileName());
        setTwitterAccount(twitterAccount);
        setHeart(new Long(status.getFavoriteCount()));
        setShared(new Long(status.getRetweetCount()));
        setTweetText(status.getText());
        
        if (status.getMediaEntities().length > 0) {
            setHasStream(Boolean.TRUE);
        }
        if (status.getGeoLocation() != null) {
            setHasGeolocation(Boolean.TRUE);
        }
        
    }
    
}
