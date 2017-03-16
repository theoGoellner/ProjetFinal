package com.bourse.facades;

import com.bourse.entities.Courtage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CourtageFacade extends AbstractFacade<Courtage> implements CourtageFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourtageFacade() {
        super(Courtage.class);
    }
    
}
