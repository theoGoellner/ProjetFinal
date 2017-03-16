package com.bourse.facades;

import com.bourse.entities.JournalActionSurOperation;
import java.util.List;
import javax.ejb.Local;

@Local
public interface JournalActionSurOperationFacadeLocal {

    void create(JournalActionSurOperation journalActionSurOperation);

    void edit(JournalActionSurOperation journalActionSurOperation);

    void remove(JournalActionSurOperation journalActionSurOperation);

    JournalActionSurOperation find(Object id);

    List<JournalActionSurOperation> findAll();

    List<JournalActionSurOperation> findRange(int[] range);

    int count();
    
}
