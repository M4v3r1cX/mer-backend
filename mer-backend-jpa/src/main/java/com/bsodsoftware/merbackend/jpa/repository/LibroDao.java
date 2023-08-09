package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bsodsoftware.merbackend.jpa.entities.Libro;

@Stateless
public class LibroDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Libro entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Libro entity) {
        em.merge(entity);
    }
    
    public Libro findById(Long id) {
        return em.find(Libro.class, id);
    }
}
