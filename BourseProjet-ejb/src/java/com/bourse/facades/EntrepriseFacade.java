package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.enumeration.EnumFormEntreprise;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EntrepriseFacade extends AbstractFacade<Entreprise> implements EntrepriseFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepriseFacade() {
        super(Entreprise.class);
    }
    
    @Override
    public List<Entreprise> getListeEntreprisesActives() {
        List<Entreprise> listeEntrActives = null;
        try {
            Query req = em.createQuery("Select e from Entreprise as e where e.dateArchivage is null");
            listeEntrActives = req.getResultList();
        } catch (Exception e) {
            listeEntrActives = null;
            System.out.println("Erreur dans la facade Entreprise dans la méthode getListeEntreprisesActives " + e.getMessage());
        }
        return listeEntrActives;
    }
    
    @Override
    public List<Entreprise> getListeEntreprisesActivesParCourtier(Employe courtier) {
        List<Entreprise> listeEntrActives = null;
        try {
            Query req = em.createQuery("Select e from Entreprise as e where e.dateArchivage is null and e.courtier=:courtier");
            req.setParameter("courtier", courtier);
            listeEntrActives = req.getResultList();
        } catch (Exception e) {
            listeEntrActives = null;
            System.out.println("Erreur dans la facade Entreprise dans la méthode getListeEntreprisesActivesParCourtier " + e.getMessage());
        }
        return listeEntrActives;
    }
    
    @Override
    public Entreprise creerEntreprise(String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau, Employe courtier) {
        Entreprise entr = new Entreprise();
        entr.setSiret(siret);
        entr.setNomEntreprise(nomEntreprise.toUpperCase());
        entr.setFormeSociete(formeEntreprise);
        entr.setContact(contact);
        entr.setTphContact(tphContact);
        entr.setTelephone(telephone);
        entr.setMail(email);
        entr.setAdresse(adresse);
        entr.setNiveau(niveau);
        entr.setCourtier(courtier);
        em.persist(entr);
        return entr;
    }
    
    @Override
    public void modifierEntreprise(Entreprise entr, String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau) {
        entr.setSiret(siret);
        entr.setNomEntreprise(nomEntreprise.toUpperCase());
        entr.setFormeSociete(formeEntreprise);
        entr.setContact(contact);
        entr.setTphContact(tphContact);
        entr.setTelephone(telephone);
        entr.setMail(email);
        entr.setAdresse(adresse);
        entr.setNiveau(niveau);
        em.merge(entr);  
    }

    @Override
    public Entreprise rechercherEntrepriseParSIRET(String siret) {
        Entreprise part = null;
        try {
            Query req = em.createQuery("select e from Entreprise as e where e.Siret = UPPER(:siret)");
            req.setParameter("siret", siret);
            part = (Entreprise) req.getSingleResult();
        } catch (Exception e) {
            part=null;
            System.out.println("Erreur dans la facade Entreprise dans la méthode rechercherEntrepriseParSIRET " + e.getMessage());
        }
        return part;
    }

    @Override
    public List<Entreprise> rechercheListeEntreprisesParCourtierParNomSiret(Employe courtier, String siret, String nom) {
    List<Entreprise> listEntr = null;
        try {
            Query req = em.createQuery("select e from Entreprise as e where e.courtier = :courtier and (e.Siret like '%:siret%' or e.nomEntreprise like '%UPPER(:nom)%') and p.dateArchivage is null");
            req.setParameter("nom", nom);
            req.setParameter("siret", siret);
            req.setParameter("courtier", courtier);
            
            listEntr = req.getResultList();
        } catch (Exception e) {
            listEntr=null;
            System.out.println("Erreur dans la facade Particulier dans la méthode rechercheListeEntreprisesParCourtierParNomSiret " + e.getMessage());
        }
        return listEntr;   
    }

    

    
    
    
}
