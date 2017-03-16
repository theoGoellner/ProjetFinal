package com.bourse.facades;

import com.bourse.entities.Marche;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MarcheFacade extends AbstractFacade<Marche> implements MarcheFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MarcheFacade() {
        super(Marche.class);
    }
    
}
