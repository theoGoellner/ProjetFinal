package com.bourse.facades;

import com.bourse.entities.Operation;
import java.util.List;
import javax.ejb.Local;

@Local
public interface OperationFacadeLocal {

    void create(Operation operation);

    void edit(Operation operation);

    void remove(Operation operation);

    Operation find(Object id);

    List<Operation> findAll();

    List<Operation> findRange(int[] range);

    int count();
    
}
