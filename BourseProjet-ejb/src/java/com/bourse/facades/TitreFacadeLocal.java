package com.bourse.facades;

import com.bourse.entities.Titre;
import java.util.List;
import javax.ejb.Local;

@Local
public interface TitreFacadeLocal {

    void create(Titre titre);

    void edit(Titre titre);

    void remove(Titre titre);

    Titre find(Object id);

    List<Titre> findAll();

    List<Titre> findRange(int[] range);

    int count();
    
}
