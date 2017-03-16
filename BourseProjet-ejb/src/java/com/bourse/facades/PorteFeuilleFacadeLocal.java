package com.bourse.facades;

import com.bourse.entities.Contrat;
import com.bourse.entities.PorteFeuille;
import java.util.List;
import javax.ejb.Local;

@Local
public interface PorteFeuilleFacadeLocal {

    void create(PorteFeuille porteFeuille);

    void edit(PorteFeuille porteFeuille);

    void remove(PorteFeuille porteFeuille);

    PorteFeuille find(Object id);

    List<PorteFeuille> findAll();

    List<PorteFeuille> findRange(int[] range);

    int count();

    PorteFeuille creerPorteFeuille(Double montantInitial, Contrat contrat);

    PorteFeuille rechercherPorteFeuilleParID(Long idPorteFeuille);
    
}
