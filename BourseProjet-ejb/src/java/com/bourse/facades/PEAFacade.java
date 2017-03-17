package com.bourse.facades;

import com.bourse.entities.Contrat;
import com.bourse.entities.PEA;
import java.util.Date;
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

    @Override
    public PEA creerPEA(Date dateOuverture,  Double montantInitial, Contrat contrat) {
        PEA pea = new PEA();
        pea.setDateOuverture(dateOuverture);
        pea.setMontantInitial(montantInitial);
        pea.setLiquidite(montantInitial);
        pea.setLeContrat(contrat);
        em.persist(pea);
        return pea;
    }
    
    
}
