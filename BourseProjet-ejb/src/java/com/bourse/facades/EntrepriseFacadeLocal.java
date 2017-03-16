package com.bourse.facades;

import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.enumeration.EnumFormEntreprise;
import java.util.List;
import javax.ejb.Local;

@Local
public interface EntrepriseFacadeLocal {

    void create(Entreprise entreprise);

    void edit(Entreprise entreprise);

    void remove(Entreprise entreprise);

    Entreprise find(Object id);

    List<Entreprise> findAll();

    List<Entreprise> findRange(int[] range);

    int count();
    
    Entreprise creerEntreprise(String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau, Employe courtier);

    void modifierEntreprise(Entreprise entr, String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau);

    Entreprise rechercherEntrepriseParSIRET(String siret);

    List<Entreprise> getListeEntreprisesActives();

    List<Entreprise> getListeEntreprisesActivesParCourtier(Employe courtier);

    List<Entreprise> rechercheListeEntreprisesParCourtierParNomSiret(Employe courtier, String siret, String nom);
}
