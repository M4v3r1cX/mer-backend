package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bsodsoftware.merbackend.jpa.entities.Red;

@Stateless
public class RedDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Red entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Red entity) {
        em.merge(entity);
    }
    
    public Red findById(Long id) {
        return em.find(Red.class, id);
    }
}
