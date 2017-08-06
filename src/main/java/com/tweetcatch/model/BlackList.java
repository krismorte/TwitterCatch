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
@Table(name = "black_list")
public class BlackList extends IdentityAndAudit {

    @OneToOne
    private TwitterAccount twitterAccount;

    private Long profileBlockedId;

    @Column(length = 150)
    private String profileBlockedName;

    private Long tweetCount;

    public BlackList() {
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
     * @return the profileBlockedId
     */
    public Long getProfileBlockedId() {
        return profileBlockedId;
    }

    /**
     * @param profileBlockedId the profileBlockedId to set
     */
    public void setProfileBlockedId(Long profileBlockedId) {
        this.profileBlockedId = profileBlockedId;
    }

    /**
     * @return the profileBlockedName
     */
    public String getProfileBlockedName() {
        return profileBlockedName;
    }

    /**
     * @param profileBlockedName the profileBlockedName to set
     */
    public void setProfileBlockedName(String profileBlockedName) {
        this.profileBlockedName = profileBlockedName;
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
