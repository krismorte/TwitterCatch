/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tweetcatch.repository;

import com.krismorte.simplerepository.jpa.JpaRepositoryAudit;
import com.tweetcatch.model.Proxy;
import com.tweetcatch.param.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;

/**
 *
 * @author krisnamourtscf
 */
public class ProxyRepository extends JpaRepositoryAudit<Proxy> {

    public ProxyRepository() {
        super(Proxy.class, PersistenceContext.CONTEXT);
    }

    public List<Proxy> getActive() {
        return run(entityManager -> {

            final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            final CriteriaQuery criteria = criteriaBuilder.createQuery(Proxy.class);

            entityManager.getTransaction().begin();
            final Root<Proxy> root = criteria.from(Proxy.class);
            final EntityType<Proxy> entityModel = root.getModel();

            Predicate predicate1 = criteriaBuilder.equal(root.get(entityModel.getSingularAttribute("active", Boolean.class)), true);

            criteria.select(root).where(predicate1);

            entityManager.getTransaction().commit();
            final TypedQuery<Proxy> query = entityManager.createQuery(criteria);
            return query.getResultList();
        });
    }
    

}
