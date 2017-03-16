package com.bourse.facades;

import com.bourse.entities.Contrat;
import com.bourse.entities.PorteFeuille;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class PorteFeuilleFacade extends AbstractFacade<PorteFeuille> implements PorteFeuilleFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PorteFeuilleFacade() {
        super(PorteFeuille.class);
    }

    @Override
    public PorteFeuille creerPorteFeuille(Double montantInitial, Contrat contrat) {
        PorteFeuille porte = new PorteFeuille();
        porte.setMontantInitial(montantInitial);
        porte.setLeContrat(contrat);
        em.persist(porte);
        return porte;
    }

    @Override
    public PorteFeuille rechercherPorteFeuilleParID(Long idPorteFeuille) {
        PorteFeuille porte = null;
        try {
            Query req = em.createQuery("Select p from PorteFeuille as p where p.id = :idPorteFeuille");
            req.setParameter("idPorteFeuille", idPorteFeuille);
            porte = (PorteFeuille) req.getSingleResult();
        } catch (Exception e) {
            porte = null;
            System.out.println("Erreur dans la facade PorteFeuille dans la méthode rechercherPorteFeuilleParID " + e.getMessage());
        }
        return porte;
    }
    
    
    
}
