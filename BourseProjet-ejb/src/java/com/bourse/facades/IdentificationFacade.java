package com.bourse.facades;

import com.bourse.entities.Identification;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class IdentificationFacade extends AbstractFacade<Identification> implements IdentificationFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IdentificationFacade() {
        super(Identification.class);
    }
    
     @Override
    public void creerIdentification(String login, String pwd, String typeUser, Long idUser) {
        Identification ident = new Identification();
        ident.setLogin(login);
        ident.setPwd(pwd);
        ident.setTypeUser(typeUser);
        ident.setIdUser(idUser);
        em.persist(ident);          
    }

    @Override
    public void modifierIdentification(Identification ident, String login, String pwd) {
        ident.setLogin(login);
        ident.setPwd(pwd);
        em.merge(ident);
    }
    
    @Override
    public void archiverIdentification(Identification ident) {
        ident.setEstActif(false);
        em.merge(ident);
    }
    
    @Override
    public void verrouillerIdentification(Identification ident) {
        ident.setEstBloque(true);
        em.merge(ident);
    }

    @Override
    public void supprimerIdentification(Identification ident) {               
        em.remove(em.merge(ident));
    }

    @Override
    public Identification rechercherLoginUser(String login, String typeUser) {
        Identification ident = null;
        try {
            Query req = em.createQuery("select i from Identification as i where i.login =:login and i.typeUser =:typeUser");
            req.setParameter("login", login);
            req.setParameter("typeUser", typeUser);
            ident = (Identification) req.getSingleResult();
        } catch (Exception e) {
            ident = null;
            System.out.println("Erreur dans la facade Identification dans la méthode rechercherLoginUser " + e.getMessage());
        }
        return ident;
    }

    @Override
    public Identification rechercherIdentParIDUserEtType(Long idUser, String typeUser) {
        Identification ident = null;
        try {
            Query req = em.createQuery("select i from Identification as i where i.idUser=:idUser and i.typeUser =:typeUser");
            req.setParameter("idUser", idUser);
            req.setParameter("typeUser", typeUser);
            ident = (Identification) req.getSingleResult();
        } catch (Exception e) {
            ident = null;
            System.out.println("Erreur dans la facade Identification dans la méthode rechercherIdentParIDUserEtType " + e.getMessage());
        }
        return ident;
    }

    @Override
    public Identification rechercherIdentParLogin(String login) {
        Identification ident = null;
        try {
            Query req = em.createQuery("select i from Identification as i where i.login =:login and i.estActif=:true");
            req.setParameter("login", login);
            req.setParameter("true", true);
            ident = (Identification) req.getSingleResult();
        } catch (Exception e) {
            ident = null;
            System.out.println("Erreur dans la facade Identification dans la méthode rechercherIdentParLogin " + e.getMessage());
        }
        return ident;
    }
}
