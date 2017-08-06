/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.repository;

import com.krismorte.simplerepository.jpa.JpaRepositoryAudit;
import com.tweetcatch.param.PersistenceContext;
import com.tweetcatch.model.TwitterAccount;

/**
 *
 * @author c007329
 */
public class TwitterAccountRepository extends JpaRepositoryAudit<TwitterAccount> {

    public TwitterAccountRepository() {
        super(TwitterAccount.class, PersistenceContext.CONTEXT);
    }

}
