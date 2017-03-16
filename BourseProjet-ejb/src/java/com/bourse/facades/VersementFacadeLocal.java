package com.bourse.facades;

import com.bourse.entities.Versement;
import java.util.List;
import javax.ejb.Local;

@Local
public interface VersementFacadeLocal {

    void create(Versement versement);

    void edit(Versement versement);

    void remove(Versement versement);

    Versement find(Object id);

    List<Versement> findAll();

    List<Versement> findRange(int[] range);

    int count();
    
}
