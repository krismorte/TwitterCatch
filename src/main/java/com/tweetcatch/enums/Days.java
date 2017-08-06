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
public enum Days {
    
    Sunday(0),
    Monday(1),
    Tuesday(2),
    Wendesday(3),
    Thirdays(3),
    Friday(3),
    Saturday(3);
    
    private int id;

    Days(int id) {
        this.id = id;
    }
    
}
