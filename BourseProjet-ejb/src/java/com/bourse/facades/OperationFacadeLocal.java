package com.bourse.facades;

import com.bourse.entities.Operation;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import java.util.Date;
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
    
    void creerOperation(Titre titre, PorteFeuille pfCible, PorteFeuille pfSource, Boolean origine, int quantite, Date dateLimite);

}
