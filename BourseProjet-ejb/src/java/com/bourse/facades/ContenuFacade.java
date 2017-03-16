package com.bourse.facades;

import com.bourse.entities.Contenu;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContenuFacade extends AbstractFacade<Contenu> implements ContenuFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContenuFacade() {
        super(Contenu.class);
    }
    
}
