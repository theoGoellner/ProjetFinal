package com.bourse.sessions;

import com.bourse.entities.Employe;
import com.bourse.entities.Identification;
import com.bourse.enumeration.EnumRoleEmploye;
import com.bourse.facades.EmployeFacadeLocal;
import com.bourse.facades.IdentificationFacadeLocal;
import com.bourse.facades.JournalConnexionFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AdministrationSession implements AdministrationSessionLocal {

    @EJB
    private JournalConnexionFacadeLocal journalConnexionFacade;

    @EJB
    private IdentificationFacadeLocal identificationFacade;
    
    @EJB
    private EmployeFacadeLocal employeFacade;
        
    
    // GESTION DES IDENTIFICATIONS
   
    @Override
    public void creationIdentification(String login, String pwd, String typeUser, Long idUser) {
        identificationFacade.creerIdentification(login, pwd, typeUser, idUser);
    }
    
    @Override
    public void archivageIdentification(Identification ident) {
        identificationFacade.archiverIdentification(ident);
    }
    
    @Override
    public void verrouillageIdentification(Identification ident) {
        identificationFacade.verrouillerIdentification(ident);
    }
    
    @Override
    public void suppressionIdentification(Identification identification) {
        identificationFacade.supprimerIdentification(identification);
    }
    
    @Override
    public void ajouterConnexion(Identification ident) {
        journalConnexionFacade.ajouterConnexion(ident);
    }
    
    @Override
    public Identification rechercheIdentParIDUserEtType(Long idUser, String typeUser) {
        return identificationFacade.rechercherIdentParIDUserEtType(idUser, typeUser);
    }
    
    @Override
    public Identification rechercheLoginUser(String login, String typeUser) {
        return identificationFacade.rechercherLoginUser(login, typeUser);
    }
    
    @Override
    public Identification rechercheIdentParLogin(String login) {
        return identificationFacade.rechercherIdentParLogin(login);
    }
      
    // GESTION DES EMPLOYES   
    @Override
    public List<Employe> getListeEmployes() {
        return employeFacade.getListeEmployes();
    }
    
    @Override
    public List<Employe> getListeEmployesActifs() {
        return employeFacade.getListeEmployesActifs();
    }
    
    @Override
    public Employe creationEmploye(String nom, String prenom, String email, Date dateEmbauche, int niveau, EnumRoleEmploye role, Employe responsable) {
        return employeFacade.creerEmploye(nom, prenom, email, dateEmbauche, niveau, role, responsable);          
    }
    
    @Override
    public void modificationEmploye(Employe emp, String nom, String prenom, String email, Date dateEmbauche, int niveau) {
        employeFacade.modifierEmploye(emp, nom, prenom, email, dateEmbauche, niveau);
    }
    
    @Override
    public void archivageEmploye(Employe employe) {
        employeFacade.archiverEmploye(employe);
        identificationFacade.archiverIdentification(identificationFacade.rechercherIdentParIDUserEtType(employe.getId(), "Employe"));
    }
   
    @Override
    public Employe rechercheEmployeParNomPrenom(String nom, String prenom) {       
        return employeFacade.rechercherEmployeParNomPrenom(nom, prenom);
    }
    
    @Override
    public Employe rechercheEmployeParID(Long idEmploye) {
        return employeFacade.rechercherEmployeParID(idEmploye);
    }  

    @Override
    public Employe rechercheChefSalle() {
        return employeFacade.rechercherChefSalle();
    } 

      
}
