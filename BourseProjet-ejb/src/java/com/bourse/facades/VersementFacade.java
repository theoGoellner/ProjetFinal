package com.bourse.facades;

import com.bourse.entities.Client;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Versement;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VersementFacade extends AbstractFacade<Versement> implements VersementFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VersementFacade() {
        super(Versement.class);
    }

    @Override
    public Versement creerVersement(Client client, PorteFeuille portefeuille, Double montant) {
        Versement vers = new Versement();
        vers.setLeClient(client);
        vers.setLePortefeuille(portefeuille);
        vers.setDateVersement(new Date());
        vers.setMontant(montant);
        em.persist(vers);
        return vers;
    }
    
    
}
