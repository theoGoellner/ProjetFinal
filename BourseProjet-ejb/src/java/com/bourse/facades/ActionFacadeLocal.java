package com.bourse.facades;

import com.bourse.entities.Action;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ActionFacadeLocal {

    void create(Action action);

    void edit(Action action);

    void remove(Action action);

    Action find(Object id);

    List<Action> findAll();

    List<Action> findRange(int[] range);

    int count();
}
