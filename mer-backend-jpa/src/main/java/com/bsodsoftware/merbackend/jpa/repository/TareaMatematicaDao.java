package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bsodsoftware.merbackend.jpa.entities.TareaMatematica;

@Stateless
public class TareaMatematicaDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(TareaMatematica entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(TareaMatematica entity) {
        em.merge(entity);
    }
    
    public TareaMatematica findById(Long id) {
        return em.find(TareaMatematica.class, id);
    }
}
