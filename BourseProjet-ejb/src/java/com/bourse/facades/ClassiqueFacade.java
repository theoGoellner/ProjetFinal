package com.bourse.facades;

import com.bourse.entities.Classique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClassiqueFacade extends AbstractFacade<Classique> implements ClassiqueFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassiqueFacade() {
        super(Classique.class);
    }   
}
