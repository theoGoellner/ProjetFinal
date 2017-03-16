package com.bourse.facades;

import com.bourse.entities.Courtage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CourtageFacadeLocal {

    void create(Courtage courtage);

    void edit(Courtage courtage);

    void remove(Courtage courtage);

    Courtage find(Object id);

    List<Courtage> findAll();

    List<Courtage> findRange(int[] range);

    int count();
    
}
