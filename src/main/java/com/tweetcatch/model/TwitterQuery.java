/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "twitter_query")
public class TwitterQuery extends IdentityAndAudit {

    @Column(length = 50)
    private String restrictions;
    @Column(length = 50)
    private String languages;
    @Column(length = 400)
    private String queryString;
    @ManyToOne
    @JoinColumn
    private RetweetQueue retweetQueue;

    /**
     * @return the restrictions
     */
    public String getRestrictions() {
        return restrictions;
    }

    /**
     * @param restrictions the restrictions to set
     */
    public void setRestrictions(String restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * @return the languages
     */
    public String getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(String languages) {
        this.languages = languages;
    }

    /**
     * @return the queryString
     */
    public String getQueryString() {
        return queryString;
    }

    /**
     * @param queryString the queryString to set
     */
    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    /**
     * @return the retweetQueue
     */
    public RetweetQueue getRetweetQueue() {
        return retweetQueue;
    }

    /**
     * @param retweetQueue the retweetQueue to set
     */
    public void setRetweetQueue(RetweetQueue retweetQueue) {
        this.retweetQueue = retweetQueue;
    }


}
