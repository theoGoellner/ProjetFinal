package com.bourse.facades;

import com.bourse.entities.Contenu;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ContenuFacadeLocal {

    void create(Contenu contenu);

    void edit(Contenu contenu);

    void remove(Contenu contenu);

    Contenu find(Object id);

    List<Contenu> findAll();

    List<Contenu> findRange(int[] range);

    int count();

    void creerContenu(PorteFeuille portefeuille, Titre titre, int qte);

    Contenu rechercheContenuParPFetTitre(PorteFeuille portefeuille, Titre titre);
    
}
