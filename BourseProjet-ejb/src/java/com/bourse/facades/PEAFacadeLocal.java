package com.bourse.facades;

import com.bourse.entities.PEA;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PEAFacadeLocal {

    void create(PEA pEA);

    void edit(PEA pEA);

    void remove(PEA pEA);

    PEA find(Object id);

    List<PEA> findAll();

    List<PEA> findRange(int[] range);

    int count();
    
}
