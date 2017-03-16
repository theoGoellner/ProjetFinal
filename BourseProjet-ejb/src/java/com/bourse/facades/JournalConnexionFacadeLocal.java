package com.bourse.facades;

import com.bourse.entities.Identification;
import com.bourse.entities.JournalConnexion;
import java.util.List;
import javax.ejb.Local;

@Local
public interface JournalConnexionFacadeLocal {

    void create(JournalConnexion journalConnexion);

    void edit(JournalConnexion journalConnexion);

    void remove(JournalConnexion journalConnexion);

    JournalConnexion find(Object id);

    List<JournalConnexion> findAll();

    List<JournalConnexion> findRange(int[] range);

    int count();

    void ajouterConnexion(Identification identification);
    
}
