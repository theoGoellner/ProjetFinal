package com.bourse.facades;

import com.bourse.entities.Client;
import com.bourse.entities.Contrat;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ContratFacadeLocal {

    void create(Contrat contrat);

    void edit(Contrat contrat);

    void remove(Contrat contrat);

    Contrat find(Object id);

    List<Contrat> findAll();

    List<Contrat> findRange(int[] range);

    int count();

    Contrat creerContrat(Date dateDebut, String rib, String typeContrat, Client cli);

    Contrat rechercherContratParID(Long idContrat);
    
}
