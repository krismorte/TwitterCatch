/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.repository;

import com.krismorte.simplerepository.jpa.JpaRepository;
import com.tweetcatch.model.TweetSave;
import com.tweetcatch.param.PersistenceContext;
import java.util.List;

/**
 *
 * @author krismorte
 */
public class TweetSaveRepository extends JpaRepository<TweetSave> {

    public TweetSaveRepository() {
        super(TweetSave.class, PersistenceContext.CONTEXT );
    }
    
    public List<TweetSave> listByCreateDate(){
        
      return null;
    }
    
}
