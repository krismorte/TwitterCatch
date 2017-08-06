/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.model;

import com.krismorte.simplerepository.identity.Identity;
import com.tweetcatch.enums.Days;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author krismorte
 */
@Entity
@Table(name = "schedule_day")
public class ScheduleDay extends Identity{
    
    @ManyToOne
    @JoinColumn
    private Schedule schedule;
    
    @Enumerated(EnumType.ORDINAL)
    private Days days;

    public ScheduleDay() {
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

    /**
     * @return the days
     */
    public Days getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(Days days) {
        this.days = days;
    }
    
    
    
}
