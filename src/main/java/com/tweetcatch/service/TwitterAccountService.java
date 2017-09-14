/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.dao.AuditRule;
import com.tweetcatch.dao.TwitterAccountAccessDao;
import com.tweetcatch.dao.TwitterAccountDao;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterAccountAccess;
import java.util.Collection;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author krismorte
 */
public class TwitterAccountService {

    private TwitterManager twitterManager = new TwitterManager();

    private TwitterAccountDao twitterAccountDao = new TwitterAccountDao();
    private TwitterAccountAccessDao twitterAccountAccessDao = new TwitterAccountAccessDao();
    private final String DIR_PROFILE = "Profile_Pic";



    public boolean testConnection(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(consumerKey);
        cb.setOAuthConsumerSecret(consumerSecret);
        cb.setOAuthAccessToken(accessToken);
        cb.setOAuthAccessTokenSecret(accessTokenSecret);


        TwitterFactory tf = new TwitterFactory(cb.build());
        if (tf.getInstance() != null) {
            return true;
        } else {
            return false;
        }
    }

    public Collection<TwitterAccount> getAccounts() {
        twitterAccountDao.beginTransaction();
        Collection<TwitterAccount> lista = twitterAccountDao.findAll();
        twitterAccountDao.commitAndCloseTransaction();
        return lista;
    }

    public TwitterAccount save(String accessToken, String accessTokenSecret, String consumerKey, String consumerSecret) throws Exception {

        TwitterAccountAccess access = new TwitterAccountAccess();

        access.setAccessToken(accessToken);
        access.setAccessTokenSecret(accessTokenSecret);
        access.setConsumerKey(consumerKey);
        access.setConsumerSecret(consumerSecret);

        twitterManager.connect(access);
        User user = twitterManager.extractUser();

        twitterAccountAccessDao.beginTransaction();
        AuditRule.audit(access);
        twitterAccountAccessDao.save(access);
        twitterAccountAccessDao.commitAndCloseTransaction();

        TwitterAccount twitterAccount = new TwitterAccount(user);
        twitterAccount.setAccountAccess(access);

        if (!user.getProfileImageURL().equals("")) {
            String imagePath = TwitterStream.download(user.getProfileImageURL(), DIR_PROFILE);
            twitterAccount.setProfileImageUrl(user.getProfileImageURL());
            twitterAccount.setProfileImage(imagePath);
        }

        AuditRule.audit(twitterAccount);

        twitterAccountDao.beginTransaction();
        twitterAccountDao.save(twitterAccount);
        twitterAccountDao.commitAndCloseTransaction();
        return twitterAccount;
    }

    public boolean updateFromTwitter(TwitterAccount twitterAccount) throws Exception {

        //twitterAccount.connect();
        twitterManager.connect(twitterAccount);
        User user = twitterManager.extractUser();//twitterAccount.extractUser();

        if (twitterAccount.getProfileImageUrl() == null || !twitterAccount.getProfileImageUrl().equals(user.getProfileImageURL())) {
            String imagePath = TwitterStream.download(user.getProfileImageURL(), DIR_PROFILE);
            twitterAccount.setProfileImageUrl(user.getProfileImageURL());
            twitterAccount.setProfileImage(imagePath);
        }

        twitterAccount.setDescription(user.getDescription());
        twitterAccount.setLocation(user.getLocation());
        twitterAccount.setEmail(user.getEmail());
        twitterAccount.setTotalStatuses(new Long(user.getStatusesCount()));
        twitterAccount.setTotalFriends(new Long(user.getFriendsCount()));
        twitterAccount.setTotalFollowers(new Long(user.getFollowersCount()));

        AuditRule.audit(twitterAccount);
        twitterAccountDao.beginTransaction();
        twitterAccountDao.update(twitterAccount);
        twitterAccountDao.commitAndCloseTransaction();

        /*saveFriends(twitterAccount);
        saveFollowers(twitterAccount);
        saveAccountInteresting("hugogloss", twitterAccount);*/
        return true;
    }

    /*public void saveFriends(TwitterAccount twitterAccount) throws Exception {
        resetCounters();
        IDs ids;
        System.out.println("Listing friends's ids.");
        List<TwitterUser> users = new ArrayList<>();
        List<TwitterAccountFriend> friends = new ArrayList<>();
        RateLimitStatus rateLimitStatus;
        do {
            ids = twitterManager.getFriendsIDs(cursor);//twitter.getFriendsIDs(twitter.getScreenName(), cursor);
            rateLimitStatus = ids.getRateLimitStatus();
            for (long id : ids.getIDs()) {
                //System.out.println(id);

                twitterAccountDao.beginTransaction();
                TwitterUser twitterUser = twitterAccountDao.getByProfileId(id);//userRepository.getByProfileId(id);
                twitterAccountDao.commitAndCloseTransaction();

                //TwitterUser twitterUser = userRepository.getByProfileId(user);
                if (twitterUser == null) {
                    User user = twitterManager.getUser(id);//twitter.showUser(id);
                    rateLimitStatus = user.getRateLimitStatus();
                    apiCallsCounter++;
                    twitterUser = new TwitterUser(user);
                    users.add(twitterUser);
                    //userRepository.persist(twitterUser);
                }

                twitterAccountFriendDao.beginTransaction();
                TwitterAccountFriend friend = twitterAccountFriendDao.getByID(twitterAccount, twitterUser);
                twitterAccountFriendDao.commitAndCloseTransaction();

                //TwitterAccountFriend friend = accountFriendRepository.getByID(twitterAccount, twitterUser);
                if (friend == null) {
                    friend = new TwitterAccountFriend();
                    friend.setTwitterAccount(twitterAccount);
                    friend.setTwitterUser(twitterUser);
                    friend.setActive(true);
                    friends.add(friend);
                }

                if (rateLimitStatus.getRemaining() == 0) {
                    try {
                        twitterAccountDao.bulk(users);
                        twitterAccountFriendDao.bulk(friends);
                        users = new ArrayList<>();
                        friends = new ArrayList<>();
                        System.out.println("Pausa: " + LocalDateTime.now());
                        Thread.sleep(rateLimitStatus.getSecondsUntilReset() * 1000);
                    } catch (InterruptedException e) {

                    }
                }

            }
        } while ((cursor = ids.getNextCursor()) != 0);

        twitterAccountDao.bulk(users);
        twitterAccountFriendDao.bulk(friends);
    }

    public void saveFollowers(TwitterAccount twitterAccount) throws Exception {
        resetCounters();
        IDs ids;
        System.out.println("Listing followers's ids.");
        List<TwitterUser> users = new ArrayList<>();
        List<TwitterAccountFollower> followers = new ArrayList<>();
        RateLimitStatus rateLimitStatus;
        do {
            ids = twitterManager.getFollowersIDs(cursor);//twitter.getFriendsIDs(twitter.getScreenName(), cursor);
            rateLimitStatus = ids.getRateLimitStatus();
            apiCallsCounter++;
            for (long id : ids.getIDs()) {
                //System.out.println(id);

                twitterAccountDao.beginTransaction();
                TwitterUser twitterUser = twitterAccountDao.getByProfileId(id);//userRepository.getByProfileId(id);
                twitterAccountDao.commitAndCloseTransaction();

                //TwitterUser twitterUser = userRepository.getByProfileId(user);
                if (twitterUser == null) {
                    System.out.println("ID: " + id);
                    User user = null;
                    try {
                        user = twitterManager.getUser(id);//twitter.showUser(id);
                        twitterUser = new TwitterUser(user);
                        rateLimitStatus = user.getRateLimitStatus();
                        System.out.println("Tempo restante: " + rateLimitStatus.getRemaining());
                    } catch (TwitterException ex) {//conta suspensa
                        System.out.println("Conta Suspensa");
                        twitterUser = new TwitterUser();
                        twitterUser.setProfileId(id);
                    }

                    users.add(twitterUser);

                }
                twitterAccountFollowerDao.beginTransaction();
                TwitterAccountFollower follower = twitterAccountFollowerDao.getByID(twitterAccount, twitterUser);
                twitterAccountFollowerDao.commitAndCloseTransaction();
                //TwitterAccountFollower follower = accountFollowerRepository.getByID(twitterAccount, twitterUser);
                if (follower == null) {
                    follower = new TwitterAccountFollower();
                    follower.setTwitterAccount(twitterAccount);
                    follower.setTwitterUser(twitterUser);
                    follower.setActive(true);
                    followers.add(follower);
                }

                if (rateLimitStatus != null && rateLimitStatus.getRemaining() == 0) {
                    try {
                        twitterAccountDao.bulk(users);
                        twitterAccountFollowerDao.bulk(followers);
                        users = new ArrayList<>();
                        followers = new ArrayList<>();
                        System.out.println("Pausa: " + LocalDateTime.now());
                        Thread.sleep(rateLimitStatus.getSecondsUntilReset() * 1000);
                    } catch (InterruptedException e) {

                    }
                }

            }
        } while ((cursor = ids.getNextCursor()) != 0);

        twitterAccountDao.bulk(users);
        twitterAccountFollowerDao.bulk(followers);
    }

    public void saveAccountInteresting(String name, TwitterAccount twitterAccount) throws Exception {

        TwitterAccountInterestingService twitterAccountInterestingService = new TwitterAccountInterestingService(twitterManager);
        
        TwitterAccountInteresting accountInteresting =twitterAccountInterestingService.save(twitterAccount, name);
        
        twitterAccountInterestingService.saveFriends(accountInteresting);
        twitterAccountInterestingService.saveFollower(accountInteresting);
        
        /*accountInterestingRepository = new TwitterAccountInterestingRepository(twitterManager);
        System.out.println("Account Interesting.");
        TwitterAccountInteresting accountInteresting = accountInterestingRepository.save(twitterAccount, name);
        System.out.println("Account Interesting Friends.");
        accountInterestingRepository.saveFriends(accountInteresting);
        System.out.println("Account Interesting Followers.");
        accountInterestingRepository.saveFollower(accountInteresting);*

    }*/

 /*private void resetCounters() {
        cursor = -1;
        apiCallsCounter = 0;
        apiCallsCounterTotal = 0;
        maxExcutionTimeCounter = 0;
    }

    private boolean executionControll() {
        boolean parou = false;
        if (apiCallsCounter == maxApiCounterPer15min) {
            //apiCallsCounterTotal += apiCallsCounter;
            apiCallsCounter = 0;
            maxExcutionTimeCounter++;
            try {
                System.out.println("Pausa: " + LocalDateTime.now());
                if (maxExcutionTimeCounter == maxExcutionTime) {
                    maxExcutionTimeCounter = 0;
                    Thread.sleep(120000);//2min    
                } else {
                    Thread.sleep(60000);//1min    
                }
            } catch (Exception ex) {

            }
            parou = true;
        }
        return parou;
    }*/
}
