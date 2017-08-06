/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.IdentityAndAudit;
import com.tweetcatch.enums.Recurrence;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author krismorte
 */
@Entity
@Table(name = "schedule")
public class Schedule extends IdentityAndAudit {

    @Column(length = 40)
    private String name;
    @OneToOne
    private TwitterAccount twitterAccount;
    private LocalTime startTime;
    private LocalTime finalTime;
    private LocalDateTime singleTime;
    private LocalTime intervalTime;

    @Enumerated(EnumType.ORDINAL)
    private Recurrence recurrence;

    @OneToMany(mappedBy = "schedule")
    private List<ScheduleDay> days;    

    public Schedule() {
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
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the finalTime
     */
    public LocalTime getFinalTime() {
        return finalTime;
    }

    /**
     * @param finalTime the finalTime to set
     */
    public void setFinalTime(LocalTime finalTime) {
        this.finalTime = finalTime;
    }


    /**
     * @return the recurrence
     */
    public Recurrence getRecurrence() {
        return recurrence;
    }

    /**
     * @param recurrence the recurrence to set
     */
    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    /**
     * @return the singleTime
     */
    public LocalDateTime getSingleTime() {
        return singleTime;
    }

    /**
     * @param singleTime the singleTime to set
     */
    public void setSingleTime(LocalDateTime singleTime) {
        this.singleTime = singleTime;
    }

    /**
     * @return the intervalTime
     */
    public LocalTime getIntervalTime() {
        return intervalTime;
    }

    /**
     * @param intervalTime the intervalTime to set
     */
    public void setIntervalTime(LocalTime intervalTime) {
        this.intervalTime = intervalTime;
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
     * @return the days
     */
    public List<ScheduleDay> getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(List<ScheduleDay> days) {
        this.days = days;
    }


}
