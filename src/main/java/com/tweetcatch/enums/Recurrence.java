/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.enums;

/**
 *
 * @author krismorte
 */
public enum Recurrence {

    Period(0),
    Interval(1),
    SingleTime(2);

    private int id;

    Recurrence(int id) {
        this.id = id;
    }

}
