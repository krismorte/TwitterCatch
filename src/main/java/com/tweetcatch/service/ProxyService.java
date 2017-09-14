/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.service;

import com.tweetcatch.dao.AuditRule;
import com.tweetcatch.dao.ProxyDao;
import com.tweetcatch.model.Proxy;


/**
 *
 * @author krisnamourtscf
 */
public class ProxyService {

    //private ProxyRepository repository = new ProxyRepository();
    private ProxyDao proxyDao = new ProxyDao();

    public boolean save(Proxy proxy) {
        //repository.audit(proxy);
        //repository.persist(proxy);
        AuditRule.audit(proxy);
        proxyDao.beginTransaction();
        proxyDao.save(proxy);
        proxyDao.commitAndCloseTransaction();
        return true;
    }

    public static void configure() {
        ProxyDao proxyDaoLocal = new ProxyDao();

        proxyDaoLocal.beginTransaction();
        Proxy proxy = proxyDaoLocal.getActive();
        proxyDaoLocal.commitAndCloseTransaction();
        if (proxy != null) {
            proxy.configure();
        }

    }

}
