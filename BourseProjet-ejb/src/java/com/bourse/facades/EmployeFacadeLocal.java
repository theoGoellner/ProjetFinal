package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.enumeration.EnumRoleEmploye;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EmployeFacadeLocal {

    void create(Employe employe);

    void edit(Employe employe);

    void remove(Employe employe);

    Employe find(Object id);

    List<Employe> findAll();

    List<Employe> findRange(int[] range);

    int count();
    
    Employe creerEmploye(String nom, String prenom, String email, Date dateEmbauche, int niveau, EnumRoleEmploye role, Employe responsable);

    void modifierEmploye(Employe emp, String nom, String prenom, String email, Date dateEmbauche, int niveau);

    Employe rechercherEmployeParID(Long idEmploye);

    Employe rechercherEmployeParNomPrenom(String nom, String prenom);
    
    Employe rechercherChefSalle();

    void archiverEmploye(Employe employe);

    List<Employe> getListeEmployes();

    List<Employe> getListeEmployesActifs();

    

    
}
