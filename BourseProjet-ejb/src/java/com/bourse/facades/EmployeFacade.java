package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.enumeration.EnumRoleEmploye;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EmployeFacade extends AbstractFacade<Employe> implements EmployeFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeFacade() {
        super(Employe.class);
    }

    @Override
    public List<Employe> getListeEmployes() {
        return findAll();
    }
    
    @Override
    public List<Employe> getListeEmployesActifs() {
        List<Employe> listeEmpActifs = null;
        try {
            Query req = em.createQuery("Select e from Employe as e where e.dateFinContrat is null");
            listeEmpActifs = req.getResultList();
        } catch (Exception e) {
            listeEmpActifs = null;
            System.out.println("Erreur dans la facade Employe dans la méthode getListeEmployesActifs " + e.getMessage());
        }
        return listeEmpActifs;
    }

    @Override
    public Employe creerEmploye(String nom, String prenom, String email, Date dateEmbauche, int niveau, EnumRoleEmploye role, Employe responsable) {
        Employe emp = new Employe();
        emp.setNom(nom.toUpperCase());
        emp.setPrenom(prenom.toUpperCase());
        emp.setEmail(email);
        emp.setDateEmbauche(dateEmbauche);
        emp.setNiveau(niveau);
        emp.setRole(role);
        emp.setResponsable(responsable);
        em.persist(emp);
        return emp;
    }

    @Override
    public void modifierEmploye(Employe emp, String nom, String prenom, String email, Date dateEmbauche, int niveau) {
        emp.setNom(nom.toUpperCase());
        emp.setPrenom(prenom.toUpperCase());
        emp.setEmail(email);
        emp.setDateEmbauche(dateEmbauche);
        emp.setNiveau(niveau);
        em.merge(emp);
    }
    
    @Override
    public void archiverEmploye(Employe employe) {
        employe.setDateFinContrat(new Date());
        em.merge(employe);
    }
    
    @Override
    public Employe rechercherEmployeParID(Long idEmploye) {
        Employe emp = null;
        try {
            Query req = em.createQuery("Select e from Employe as e where e.id = :idEmploye");
            req.setParameter("idEmploye", idEmploye);
            emp = (Employe) req.getSingleResult();
        } catch (Exception e) {
            emp = null;
            System.out.println("Erreur dans la facade Employe dans la méthode rechercherEmployeParID " + e.getMessage());
        }
        return emp;
    }  

    @Override
    public Employe rechercherEmployeParNomPrenom(String nom, String prenom) {
        Employe emp = null;
        try {
            Query req = em.createQuery("select e from Employe as e where e.nom = UPPER(:nom) and e.prenom =UPPER(:prenom)");
            req.setParameter("nom", nom);
            req.setParameter("prenom", prenom);
            emp = (Employe) req.getSingleResult();
        } catch (Exception e) {
            emp=null;
            System.out.println("Erreur dans la facade Employe dans la méthode rechercherEmployeParNomPrenom " + e.getMessage());
        }
        return emp;
    }
    
    @Override
    public Employe rechercherChefSalle() {
        Employe emp = null;
        try {
            Query req = em.createQuery("select e from Employe as e where e.role=:role ");
            req.setParameter("role", EnumRoleEmploye.ChefSalle);
            emp = (Employe) req.getSingleResult();
        } catch (Exception e) {
            emp=null;
            System.out.println("Erreur dans la facade Employe dans la méthode rechercherChefSalle " + e.getMessage());
        }
        return emp;
    }  
    
    
}
