package com.bourse.facades;

import com.bourse.entities.Communication;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CommunicationFacadeLocal {

    void create(Communication communication);

    void edit(Communication communication);

    void remove(Communication communication);

    Communication find(Object id);

    List<Communication> findAll();

    List<Communication> findRange(int[] range);

    int count();
    
}
