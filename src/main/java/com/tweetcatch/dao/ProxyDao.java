/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao;

import com.tweetcatch.model.Proxy;
import javax.persistence.NoResultException;

/**
 *
 * @author krismorte
 */
public class ProxyDao extends GenericDao<Proxy> {

    private static final long serialVersionUID = 1L;

    public ProxyDao() {
        super(Proxy.class);
    }

    public Proxy getActive() {
        Proxy proxy = null;

        try {
            proxy = getEntityManager().createQuery("select p from Proxy p where p.active=true",
                     Proxy.class)
                    .getSingleResult();

        } catch (NoResultException ex) {
            return proxy;
        }

        return proxy;
    }
    
}
