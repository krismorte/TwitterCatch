/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao;

import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterUser;
import com.tweetcatch.model.User;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author c007329
 */
public class TwitterUserDao extends GenericDao<TwitterUser> {

    private static final long serialVersionUID = 1L;

    public TwitterUserDao() {
        super(TwitterUser.class);
    }

    public TwitterUser getByProfileId(Long id) {
        TwitterUser twitterUser = null;

        try {
            twitterUser = getEntityManager().createQuery("select t from TwitterUser t where t.profileId=:profileId ",
                    TwitterUser.class)
                    .setParameter("profileId", id)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return twitterUser;
        }
        return twitterUser;

    }

    public TwitterUser getByProfileId(User user) {
        TwitterUser twitterUser = null;

        try {
            twitterUser = getEntityManager().createQuery("select t from TwitterUser t where t.profileId=:profileId ",
                    TwitterUser.class)
                    .setParameter("profileId", user.getId())
                    .getSingleResult();

        } catch (NoResultException ex) {
            return twitterUser;
        }

        return twitterUser;

    }

    public List<TwitterUser>listByAccount(TwitterAccount account) {
        List<TwitterUser> list = null;

        try {
            list = getEntityManager().createQuery("select t from TwitterUser t where t.twitterAccount=:twitterAccount ",
                    TwitterUser.class)
                    .setParameter("twitterAccount", account)
                    .getResultList();

        } catch (NoResultException ex) {
            return list;
        }
        return list;

    }

}
