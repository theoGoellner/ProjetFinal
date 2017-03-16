package com.bourse.facades;

import com.bourse.entities.PERP;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PERPFacadeLocal {

    void create(PERP pERP);

    void edit(PERP pERP);

    void remove(PERP pERP);

    PERP find(Object id);

    List<PERP> findAll();

    List<PERP> findRange(int[] range);

    int count();
    
}
