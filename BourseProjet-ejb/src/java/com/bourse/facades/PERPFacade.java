package com.bourse.facades;

import com.bourse.entities.PERP;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PERPFacade extends AbstractFacade<PERP> implements PERPFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PERPFacade() {
        super(PERP.class);
    }

    @Override
    public PERP creerPERP(Date dateOuverture, Date dateFermeture) {
        PERP perp = new PERP();
        perp.setDateOuverture(dateOuverture);
        perp.setDateFermeture(dateFermeture);
        em.persist(perp);
        return perp;
    }
    
    
}
