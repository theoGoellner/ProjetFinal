package com.bourse.facades;

import com.bourse.entities.Marche;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MarcheFacadeLocal {

    void create(Marche marche);

    void edit(Marche marche);

    void remove(Marche marche);

    Marche find(Object id);

    List<Marche> findAll();

    List<Marche> findRange(int[] range);

    int count();
    
}
