package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.entities.Particulier;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ParticulierFacadeLocal {

    void create(Particulier particulier);

    void edit(Particulier particulier);

    void remove(Particulier particulier);

    Particulier find(Object id);

    List<Particulier> findAll();

    List<Particulier> findRange(int[] range);

    int count();

    Particulier creerParticulier(String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau, Employe courtier);

    void modifierParticulier(Particulier part, String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau);

    Particulier rechercherParticulierParNomPrenom(String nom, String prenom);

    List<Particulier> getListeParticuliersActifs();
    
    List<Particulier> getListeParticuliersActifsParCourtier(Employe courtier);

    List<Particulier> rechercherListeParticuliersParCourtierParNomPrenom(Employe courtier, String nom, String prenom);
    
}
