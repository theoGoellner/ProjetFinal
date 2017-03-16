package com.bourse.facades;

import com.bourse.entities.PEA;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PEAFacade extends AbstractFacade<PEA> implements PEAFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PEAFacade() {
        super(PEA.class);
    }
    
}
