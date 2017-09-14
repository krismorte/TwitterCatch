/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.dao.ReTweetDao;
import com.tweetcatch.dao.TweetDao;
import com.tweetcatch.dao.TwitterAccountDao;

/**
 *
 * @author krismorte
 */
public class StatisticsService {

    private TwitterAccountDao twitterAccountDao = new TwitterAccountDao();
    private TweetDao tweetDao = new TweetDao();
    private ReTweetDao reTweetDao = new ReTweetDao();

    public Long totalProfiles;
    public Long totalTweets;
    public Long totalRetweets;

    public StatisticsService() {
        twitterAccountDao.beginTransaction();
        totalProfiles = twitterAccountDao.total();
        twitterAccountDao.commitAndCloseTransaction();
        tweetDao.beginTransaction();
        totalTweets = tweetDao.total();
        tweetDao.commitAndCloseTransaction();
        reTweetDao.beginTransaction();
        totalRetweets = reTweetDao.total();
        reTweetDao.commitAndCloseTransaction();
    }

}
