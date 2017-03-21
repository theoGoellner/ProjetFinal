package com.bourse.facades;

import com.bourse.entities.Contenu;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public void creerContenu(PorteFeuille portefeuille, Titre titre, int qte) {
        Contenu cont = new Contenu();
        cont.setLePorteFeuille(portefeuille);
        cont.setTitre(titre);
        cont.setQuantiteDisponible(qte);
        em.persist(cont);
    }

    @Override
    public Contenu rechercheContenuParPFetTitre(PorteFeuille portefeuille, Titre titre) {
        Contenu cont = null;
        try {
            Query req = em.createQuery("Select c from Contenu as c where (c.lePorteFeuille = :portefeuille and c.titre = :titre)");
            req.setParameter("portefeuille", portefeuille);
            req.setParameter("titre", titre);
            cont = (Contenu) req.getSingleResult();
        } catch (Exception e) {
            cont = null;
            System.out.println("Erreur dans la facade Contenu dans la méthode rechercheContenuParPFetTitre " + e.getMessage());
        }
        return cont;
    }
    
    
}
