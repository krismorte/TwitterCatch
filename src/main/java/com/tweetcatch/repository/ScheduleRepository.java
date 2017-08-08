/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.repository;

import com.krismorte.simplerepository.jpa.JpaRepositoryAudit;
import com.tweetcatch.model.Schedule;
import com.tweetcatch.param.PersistenceContext;

/**
 *
 * @author c007329
 */
public class ScheduleRepository  extends JpaRepositoryAudit<Schedule> {

    public ScheduleRepository() {
        super(Schedule.class, PersistenceContext.CONTEXT);
    }
    
    
    
}
