package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bsodsoftware.merbackend.jpa.entities.Nivel;

@Stateless
public class NivelDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Nivel entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Nivel entity) {
        em.merge(entity);
    }
    
    public Nivel findById(Long id) {
        return em.find(Nivel.class, id);
    }
}
