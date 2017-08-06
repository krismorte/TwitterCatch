/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "hashtag")
public class HashTag  extends IdentityAndAudit{
 
    @Column(length = 150)
    private String hashtagText;

    @OneToOne
    private TwitterAccount twitterAccount;

    @OneToOne(targetEntity = BlackList.class)
    private BlackList blackList;

    private Long tweetCount;

    public HashTag() {
    }

    /**
     * @return the hashtagText
     */
    public String getHashtagText() {
        return hashtagText;
    }

    /**
     * @param hashtagText the hashtagText to set
     */
    public void setHashtagText(String hashtagText) {
        this.hashtagText = hashtagText;
    }

    /**
     * @return the twitterAccount
     */
    public TwitterAccount getTwitterAccount() {
        return twitterAccount;
    }

    /**
     * @param twitterAccount the twitterAccount to set
     */
    public void setTwitterAccount(TwitterAccount twitterAccount) {
        this.twitterAccount = twitterAccount;
    }

    /**
     * @return the blackList
     */
    public BlackList getBlackList() {
        return blackList;
    }

    /**
     * @param blackList the blackList to set
     */
    public void setBlackList(BlackList blackList) {
        this.blackList = blackList;
    }

    /**
     * @return the tweetCount
     */
    public Long getTweetCount() {
        return tweetCount;
    }

    /**
     * @param tweetCount the tweetCount to set
     */
    public void setTweetCount(Long tweetCount) {
        this.tweetCount = tweetCount;
    }
    
    
    
}
