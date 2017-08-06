/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;


import com.krismorte.simplerepository.identity.Identity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "banned_hashtag")
public class BannedHashTag extends Identity  {
    
    @Column(length = 150)
    private String hashtagText;

    @Column(length = 150)
    private String profileBlockedName;

    public BannedHashTag() {
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
    
    
    
}
