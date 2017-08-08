/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.enums.Recurrence;
import com.tweetcatch.model.Schedule;
import com.tweetcatch.repository.ScheduleRepository;
import java.time.LocalDateTime;

/**
 *
 * @author c007329
 */
public class ScheduleService {

    private ScheduleRepository repository = new ScheduleRepository();

    public Schedule saveSingleTimeSchedule(LocalDateTime dateTime) {

        Schedule schedule = new Schedule();
        schedule.setSingleTime(dateTime);
        schedule.setRecurrence(Recurrence.SingleTime);
        repository.audit(schedule);
        repository.persist(schedule);
        return schedule;
    }

}
