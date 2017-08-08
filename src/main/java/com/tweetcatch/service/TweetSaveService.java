/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.enums.TweetType;
import com.tweetcatch.model.Schedule;
import com.tweetcatch.model.TweetSave;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.repository.TweetSaveRepository;
import com.tweetcatch.repository.TwitterAccountRepository;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 *
 * @author krismorte
 */
public class TweetSaveService {

    private TweetSaveRepository repository = new TweetSaveRepository();
    private TwitterAccountRepository accountRepository = new TwitterAccountRepository();

    public Collection<TwitterAccount> getAccounts() {
        return accountRepository.get();
    }

    private TweetSave newTweetSave(TwitterAccount twitterAccount, String text) {
        TweetSave tweetSave = new TweetSave();
        tweetSave.setCreateDate(LocalDateTime.now());
        tweetSave.setTweetText(text);
        tweetSave.setTweetType(TweetType.MyTweet);
        tweetSave.setTwitterAccount(twitterAccount);
        tweetSave.setComments(Long.parseLong("0"));
        tweetSave.setHeart(Long.parseLong("0"));
        tweetSave.setShared(Long.parseLong("0"));
        return tweetSave;
    }

    public boolean sendSimpleTweet(TwitterAccount twitterAccount, String text) throws Exception {

        TweetSave tweetSave = newTweetSave(twitterAccount, text);

        twitterAccount.connect();
        Long id = twitterAccount.tweet(text);

        tweetSave.setTweetId(id);
        tweetSave.setSaveDate(LocalDateTime.now());

        repository.persist(tweetSave);

        return true;
    }

    public boolean saveScheduleTweet(TwitterAccount twitterAccount, LocalDateTime dateTime, String text) throws Exception {

        ScheduleService scheduleService = new ScheduleService();

        TweetSave tweetSave = newTweetSave(twitterAccount, text);

        Schedule schedule = scheduleService.saveSingleTimeSchedule(dateTime);
        tweetSave.setSchedule(schedule);

        repository.persist(tweetSave);

        return true;
    }

}
