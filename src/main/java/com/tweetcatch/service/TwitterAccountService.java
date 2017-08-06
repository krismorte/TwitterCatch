/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.repository.TwitterAccountRepository;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author krismorte
 */
public class TwitterAccountService {
    
    private  TwitterAccountRepository accountRepository = new TwitterAccountRepository();
    
    public boolean testConnection(String consumerKey,String consumerSecret,String accessToken,String accessTokenSecret){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        if(tf.getInstance()!=null){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean save(TwitterAccount twitterAccount)throws Exception{
        if(!testConnection(twitterAccount.getConsumerKey(),twitterAccount.getConsumerSecret(),twitterAccount.getAccessToken(),twitterAccount.getAccessTokenSecret())){
            throw new Exception("Connection failure");
        }
        accountRepository.audit(twitterAccount);
        accountRepository.persist(twitterAccount);
        return true;
    }
    
}
