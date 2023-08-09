package com.bsodsoftware.merbackend.jpa.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bsodsoftware.merbackend.jpa.entities.Usuario;

@Stateless
public class UsuarioDao {
	@PersistenceContext(name = "mer-backend")
    private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void save(Usuario entity) {
        em.persist(entity);
        em.flush();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void update(Usuario entity) {
        em.merge(entity);
    }
    
    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public Usuario findByEmail(String email) {
    	Query query = em.createNamedQuery("Usuario.findByEmail");
    	query.setParameter("email", email);
    	return (Usuario)query.getSingleResult();
    }
}
