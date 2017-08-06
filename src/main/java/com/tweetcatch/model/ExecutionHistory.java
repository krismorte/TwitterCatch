/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.Identity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author c007329
 */
@Entity
@Table(name = "execution_history")
public class ExecutionHistory extends Identity {
   

    private LocalDateTime runTime;

    private Integer totalTweets;

    private Integer totalMencoes;

    private Integer totalTweetTL;

    @OneToOne
    private TwitterAccount twitterAccount;

    public ExecutionHistory() {
    }

    /**
     * @return the runTime
     */
    public LocalDateTime getRunTime() {
        return runTime;
    }

    /**
     * @param runTime the runTime to set
     */
    public void setRunTime(LocalDateTime runTime) {
        this.runTime = runTime;
    }

    /**
     * @return the totalTweets
     */
    public Integer getTotalTweets() {
        return totalTweets;
    }

    /**
     * @param totalTweets the totalTweets to set
     */
    public void setTotalTweets(Integer totalTweets) {
        this.totalTweets = totalTweets;
    }

    /**
     * @return the totalMencoes
     */
    public Integer getTotalMencoes() {
        return totalMencoes;
    }

    /**
     * @param totalMencoes the totalMencoes to set
     */
    public void setTotalMencoes(Integer totalMencoes) {
        this.totalMencoes = totalMencoes;
    }

    /**
     * @return the totalTweetTL
     */
    public Integer getTotalTweetTL() {
        return totalTweetTL;
    }

    /**
     * @param totalTweetTL the totalTweetTL to set
     */
    public void setTotalTweetTL(Integer totalTweetTL) {
        this.totalTweetTL = totalTweetTL;
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
    
    
    
}
