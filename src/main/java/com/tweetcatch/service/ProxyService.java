/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.model.Proxy;
import com.tweetcatch.repository.ProxyRepository;
import java.util.List;
//import java.util.Set;
//import java.util.function.Predicate;

/**
 *
 * @author krisnamourtscf
 */
public class ProxyService {

    private ProxyRepository repository = new ProxyRepository();

    public boolean save(Proxy proxy) {
        repository.audit(proxy);
        repository.persist(proxy);
        return true;
    }

    public static void configure() {
        ProxyRepository repositoryLocal = new ProxyRepository();

        //Predicate activePredicate = (s) -> s.getActive();
        
        //Set<Proxy> proxies = repositoryLocal.get(activePredicate);
        List<Proxy> proxies = repositoryLocal.getActive();

        if (!proxies.isEmpty()) {
            Proxy activeProxy = proxies.iterator().next();
            activeProxy.configure();
        }
    }

}
