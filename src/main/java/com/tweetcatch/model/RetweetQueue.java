/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author krismorte
 */
@Entity
@Table(name = "retweet_queue")
public class RetweetQueue extends IdentityAndAudit {
    
    @Column(length = 40)
    private String name;
    
    @OneToOne
    private TwitterAccount twitterAccount;
    
    @OneToMany(mappedBy = "retweetQueue")
    private List<TwitterQuery> twitterQueries;
    
    @OneToOne
    private Schedule schedule;

    public RetweetQueue() {
    }
    
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the twitterQueries
     */
    public List<TwitterQuery> getTwitterQueries() {
        return twitterQueries;
    }

    /**
     * @param twitterQueries the twitterQueries to set
     */
    public void setTwitterQueries(List<TwitterQuery> twitterQueries) {
        this.twitterQueries = twitterQueries;
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
