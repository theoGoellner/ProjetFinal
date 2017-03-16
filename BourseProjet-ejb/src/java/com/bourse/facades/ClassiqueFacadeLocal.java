package com.bourse.facades;

import com.bourse.entities.Classique;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClassiqueFacadeLocal {

    void create(Classique classique);

    void edit(Classique classique);

    void remove(Classique classique);

    Classique find(Object id);

    List<Classique> findAll();

    List<Classique> findRange(int[] range);

    int count();
    
}
