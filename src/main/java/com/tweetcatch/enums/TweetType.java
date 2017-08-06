/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.enums;

/**
 *
 * @author c007329
 */
public enum TweetType {

    MyTweet(0),
    MentionTweet(1),
    TimeLineTweet(2),
    ExceptionTweet(3);

    private int id;

    TweetType(int id) {
        this.id = id;
    }

}
