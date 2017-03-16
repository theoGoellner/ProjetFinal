package com.bourse.facades;

import com.bourse.entities.Obligation;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ObligationFacadeLocal {

    void create(Obligation obligation);

    void edit(Obligation obligation);

    void remove(Obligation obligation);

    Obligation find(Object id);

    List<Obligation> findAll();

    List<Obligation> findRange(int[] range);

    int count();
    
}
