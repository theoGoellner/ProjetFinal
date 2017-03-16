package com.bourse.facades;

import com.bourse.entities.Obligation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ObligationFacade extends AbstractFacade<Obligation> implements ObligationFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObligationFacade() {
        super(Obligation.class);
    }
    
}
