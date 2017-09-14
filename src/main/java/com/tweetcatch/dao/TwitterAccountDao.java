/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao;

import com.tweetcatch.dao.bulk.BulkClass;
import com.tweetcatch.model.TwitterAccount;
import com.tweetcatch.model.TwitterUser;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author c007329
 */
public class TwitterAccountDao extends GenericDao<TwitterAccount> {

    public TwitterAccountDao() {
        super(TwitterAccount.class);
    }

    public Long total() {
        Long total = new Long(0);
        try {
            total = getEntityManager().createQuery("select COUNT(t) from TwitterAccount t ",
                    Long.class)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return total;
        }
        return total;
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

    public void bulk(List<TwitterUser> user) {
        BulkClass<TwitterUser> bulk = new BulkClass<TwitterUser>(this);
        bulk.bulkSave(user);
    }

}
