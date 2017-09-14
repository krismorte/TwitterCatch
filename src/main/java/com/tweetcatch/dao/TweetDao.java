/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao;

import com.tweetcatch.model.Tweet;
import javax.persistence.NoResultException;

/**
 *
 * @author c007329
 */
public class TweetDao extends GenericDao<Tweet> {

    private static final long serialVersionUID = 1L;

    public TweetDao() {
        super(Tweet.class);
    }

    public Long total() {
        Long total = new Long(0);
        try {
            total = getEntityManager().createQuery("select COUNT(t) from Tweet t ",
                    Long.class)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return total;
        }
        return total;
    }

}
