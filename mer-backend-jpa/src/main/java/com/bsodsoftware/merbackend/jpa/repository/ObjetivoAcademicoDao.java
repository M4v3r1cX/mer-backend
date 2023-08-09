package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.bsodsoftware.merbackend.jpa.entities.ObjetivoAcademico;

@Stateless
public class ObjetivoAcademicoDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(ObjetivoAcademico entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(ObjetivoAcademico entity) {
        em.merge(entity);
    }
    
    public ObjetivoAcademico findById(Long id) {
        return em.find(ObjetivoAcademico.class, id);
    }
}
