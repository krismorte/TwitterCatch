/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.Identity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

/**
 *
 * @author krismorte
 */
@MappedSuperclass
public class Status extends Identity{
    
    private Long tweetId;

    @Column(length = 200)
    private String tweetText;

    @Column(length = 50)
    private String userName;

    private LocalDateTime createDate;   

    @OneToOne
    private TwitterAccount twitterAccount;

    private LocalDateTime saveDate;

    private Long heart;
    private Long shared;
    private Long comments;
    private Boolean hasStream;
    private Boolean hasGeolocation;

    /**
     * @return the tweetId
     */
    public Long getTweetId() {
        return tweetId;
    }

    /**
     * @param tweetId the tweetId to set
     */
    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    /**
     * @return the tweetText
     */
    public String getTweetText() {
        return tweetText;
    }

    /**
     * @param tweetText the tweetText to set
     */
    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the createDate
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
     * @return the saveDate
     */
    public LocalDateTime getSaveDate() {
        return saveDate;
    }

    /**
     * @param saveDate the saveDate to set
     */
    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate;
    }

    /**
     * @return the heart
     */
    public Long getHeart() {
        return heart;
    }

    /**
     * @param heart the heart to set
     */
    public void setHeart(Long heart) {
        this.heart = heart;
    }

    /**
     * @return the shared
     */
    public Long getShared() {
        return shared;
    }

    /**
     * @param shared the shared to set
     */
    public void setShared(Long shared) {
        this.shared = shared;
    }

    /**
     * @return the comments
     */
    public Long getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Long comments) {
        this.comments = comments;
    }

    /**
     * @return the hasStream
     */
    public Boolean getHasStream() {
        return hasStream;
    }

    /**
     * @param hasStream the hasStream to set
     */
    public void setHasStream(Boolean hasStream) {
        this.hasStream = hasStream;
    }

    /**
     * @return the hasGeolocation
     */
    public Boolean getHasGeolocation() {
        return hasGeolocation;
    }

    /**
     * @param hasGeolocation the hasGeolocation to set
     */
    public void setHasGeolocation(Boolean hasGeolocation) {
        this.hasGeolocation = hasGeolocation;
    }
    
    
    
}
