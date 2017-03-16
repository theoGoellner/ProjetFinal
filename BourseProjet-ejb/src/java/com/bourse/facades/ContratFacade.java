package com.bourse.facades;

import com.bourse.entities.Client;
import com.bourse.entities.Contrat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ContratFacade extends AbstractFacade<Contrat> implements ContratFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratFacade() {
        super(Contrat.class);
    }

    @Override
    public Contrat creerContrat(Date dateDebut, String rib, String typeContrat, Client cli) {
        Contrat contrat = new Contrat();
        contrat.setDateDebut(dateDebut);
        contrat.setRIB(rib);
        contrat.setTypeContrat(typeContrat);
        contrat.setLeClient(cli);
        em.persist(contrat);
        return contrat;
    }

    @Override
    public Contrat rechercherContratParID(Long idContrat) {
        Contrat contr = null;
        try {
            Query req = em.createQuery("Select c from Contrat as c where c.id = :idContrat");
            req.setParameter("idContrat", idContrat);
            contr = (Contrat) req.getSingleResult();
        } catch (Exception e) {
            contr = null;
            System.out.println("Erreur dans la facade Contrat dans la méthode rechercherContratParID " + e.getMessage());
        }
        return contr;
    }
    
    
    
}
