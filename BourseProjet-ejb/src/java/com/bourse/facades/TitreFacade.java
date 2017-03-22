package com.bourse.facades;

import com.bourse.entities.Titre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class TitreFacade extends AbstractFacade<Titre> implements TitreFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TitreFacade() {
        super(Titre.class);
    }

    @Override
    public Titre rechercherTitreParID(Long idTitre) {
        Titre titre = null;
        try {
            Query req = em.createQuery("Select t from Titre as t where t.id = :idTitre");
            req.setParameter("idTitre", idTitre);
            titre = (Titre) req.getSingleResult();
        } catch (Exception e) {
            titre = null;
            System.out.println("Erreur dans la facade Titre dans la méthode rechercherTitreParID " + e.getMessage());
        }
        return titre;
    }
    
    
}
