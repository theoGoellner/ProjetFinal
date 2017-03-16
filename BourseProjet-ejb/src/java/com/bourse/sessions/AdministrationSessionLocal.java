package com.bourse.sessions;

import com.bourse.entities.Employe;
import com.bourse.entities.Identification;
import com.bourse.enumeration.EnumRoleEmploye;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AdministrationSessionLocal {

    Employe creationEmploye(String nom, String prenom, String email, Date dateEmbauche, int niveau, EnumRoleEmploye role, Employe responsable);

    Employe rechercheEmployeParNomPrenom(String nom, String prenom);

    Identification rechercheLoginUser(String login, String typeUser);

    void creationIdentification(String login, String pwd, String typeUser, Long idUser);

    Employe rechercheEmployeParID(Long idEmploye);

    void archivageEmploye(Employe employe);

    Identification rechercheIdentParIDUserEtType(Long idUser, String typeUser);

    void suppressionIdentification(Identification identification);

    List<Employe> getListeEmployes();

    Employe rechercheChefSalle();

    List<Employe> getListeEmployesActifs();

    void archivageIdentification(Identification ident);

    Identification rechercheIdentParLogin(String login);

    void verrouillageIdentification(Identification ident);

    void ajouterConnexion(Identification ident);

    void modificationEmploye(Employe emp, String nom, String prenom, String email, Date dateEmbauche, int niveau);
    
}
