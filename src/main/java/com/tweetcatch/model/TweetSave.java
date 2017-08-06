/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.Identity;
import com.tweetcatch.enums.TweetType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "tweet_save")
public class TweetSave extends Identity {

    private Long tweetId;

    @Column(length = 200)
    private String tweetText;

    @Column(length = 50)
    private String userName;

    private LocalDateTime createDate;   

    @OneToOne
    private TwitterAccount twitterAccount;

    private LocalDateTime saveDate;

    @Enumerated(EnumType.ORDINAL)
    private TweetType tweetType;

    private Long heart;
    private Long shared;
    private Long comments;
    private Boolean hasStream;
    
    @OneToOne
    private Schedule schedule;
    
    public TweetSave() {
    }

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
     * @return the tweetType
     */
    public TweetType getTweetType() {
        return tweetType;
    }

    /**
     * @param tweetType the tweetType to set
     */
    public void setTweetType(TweetType tweetType) {
        this.tweetType = tweetType;
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
     * @return the schedule
     */
    public Schedule getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    
    
}
