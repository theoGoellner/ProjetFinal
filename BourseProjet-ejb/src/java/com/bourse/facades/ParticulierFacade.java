package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.entities.Particulier;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ParticulierFacade extends AbstractFacade<Particulier> implements ParticulierFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticulierFacade() {
        super(Particulier.class);
    }
    
    @Override
    public List<Particulier> getListeParticuliersActifs() {
        List<Particulier> listePartActifs = null;
        try {
            Query req = em.createQuery("Select p from Particulier as p where p.dateArchivage is null");
            listePartActifs = req.getResultList();
        } catch (Exception e) {
            listePartActifs = null;
            System.out.println("Erreur dans la facade Particulier dans la méthode getListeParticuliersActifs " + e.getMessage());
        }
        return listePartActifs;
    }
    
    @Override
    public List<Particulier> getListeParticuliersActifsParCourtier(Employe courtier) {
        List<Particulier> listePartActifs = null;
        try {
            Query req = em.createQuery("Select p from Particulier as p where p.dateArchivage is null and p.courtier=:courtier");
            req.setParameter("courtier", courtier);
            listePartActifs = req.getResultList();
        } catch (Exception e) {
            listePartActifs = null;
            System.out.println("Erreur dans la facade Particulier dans la méthode getListeParticuliersActifsParCourtier " + e.getMessage());
        }
        return listePartActifs;
    }

    @Override
    public Particulier creerParticulier(String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau, Employe courtier) {
        Particulier part = new Particulier();
        part.setNom(nom.toUpperCase());
        part.setPrenom(prenom.toUpperCase());
        part.setDateNais(dateNais);
        part.setLieuNaissance(lieuNais);
        part.setTelephone(telephone);
        part.setMail(email);
        part.setAdresse(adresse);
        part.setNiveau(niveau);
        part.setCourtier(courtier);
        em.persist(part);
        return part;
    }

    @Override
    public void modifierParticulier(Particulier part, String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau) {
        part.setNom(nom.toUpperCase());
        part.setPrenom(prenom.toUpperCase());
        part.setDateNais(dateNais);
        part.setLieuNaissance(lieuNais);
        part.setTelephone(telephone);
        part.setMail(email);
        part.setAdresse(adresse);
        part.setNiveau(niveau);
        em.merge(part);  
    }

    @Override
    public Particulier rechercherParticulierParNomPrenom(String nom, String prenom) {
        Particulier part = null;
        try {
            Query req = em.createQuery("select p from Particulier as p where p.nom = UPPER(:nom) and p.prenom =UPPER(:prenom)");
            req.setParameter("nom", nom);
            req.setParameter("prenom", prenom);
            part = (Particulier) req.getSingleResult();
        } catch (Exception e) {
            part=null;
            System.out.println("Erreur dans la facade Particulier dans la méthode rechercherParticulierParNomPrenom " + e.getMessage());
        }
        return part;
    }

    @Override
    public List<Particulier> rechercherListeParticuliersParCourtierParNomPrenom(Employe courtier, String nom, String prenom) {
    List<Particulier> listPart = null;
        try {
            Query req = em.createQuery("select p from Particulier as p where p.courtier = :courtier and (p.nom like '%UPPER(:nom)%' or p.prenom like '%UPPER(:prenom)%') and p.dateArchivage is null");
            req.setParameter("nom", nom);
            req.setParameter("prenom", prenom);
            req.setParameter("courtier", courtier);
            
            listPart = req.getResultList();
        } catch (Exception e) {
            listPart=null;
            System.out.println("Erreur dans la facade Particulier dans la méthode rechercheListeParticuliersParCourtierParNomPrenom " + e.getMessage());
        }
        return listPart;
    }

    
    
    
    
    
}
