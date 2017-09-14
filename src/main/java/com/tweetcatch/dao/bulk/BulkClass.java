/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.dao.bulk;


import com.krismorte.simplerepository.identity.IdentityAndAudit;
import com.tweetcatch.dao.AuditRule;
import com.tweetcatch.dao.GenericDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author krisnamourtscf
 */
public class BulkClass<T> {

    private GenericDao genericDao;
    private final int batchSize = 100;

    public BulkClass(GenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Collection<T> bulkSave(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int i = 0;
        genericDao.beginTransaction();
        for (T t : entities) {
            
            savedEntities.add(persistOrMerge(t));
            i++;
            if (i % batchSize == 0) {
                // Flush a batch of inserts and release memory.     
                i = 0;
                genericDao.getEntityManager().flush();

                genericDao.getEntityManager().clear();
            }
        }
        genericDao.commit();
        return savedEntities;
    }

    private T persistOrMerge(T t) {
        if (((IdentityAndAudit) t).getCreateTime() == null) {
            AuditRule.audit((IdentityAndAudit) t);
            genericDao.getEntityManager().persist(t);
            return t;
        } else {
            AuditRule.audit((IdentityAndAudit) t);
            return genericDao.getEntityManager().merge(t);
        }
    }

}
