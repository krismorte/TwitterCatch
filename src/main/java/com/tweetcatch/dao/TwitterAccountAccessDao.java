/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao;

import com.tweetcatch.model.TwitterAccountAccess;

/**
 *
 * @author c007329
 */
public class TwitterAccountAccessDao extends GenericDao<TwitterAccountAccess> {

    private static final long serialVersionUID = 1L;

    public TwitterAccountAccessDao() {
        super(TwitterAccountAccess.class);
    }

}
