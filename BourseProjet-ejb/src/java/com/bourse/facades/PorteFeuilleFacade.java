package com.bourse.facades;

import com.bourse.entities.Client;
import com.bourse.entities.PorteFeuille;
import java.util.List;
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

    @Override
    public List<PorteFeuille> getListePFParClient(Client client) {
        List<PorteFeuille> lesPFs = null;
        try {
            Query req = em.createQuery("Select p from PorteFeuille as p where p.leContrat.leClient = :client");
            req.setParameter("client", client);
            lesPFs = req.getResultList();
        } catch (Exception e) {
            lesPFs = null;
            System.out.println("Erreur dans la facade PorteFeuille dans la méthode getListePFParClient " + e.getMessage());
        }
        return lesPFs;
    }

    @Override
    public Double verserMontantPF(PorteFeuille pf, Double montant) {
        pf.setLiquidite((pf.getLiquidite()+montant));
        em.merge(pf);
        return pf.getLiquidite();
    }
    
    
    
}
