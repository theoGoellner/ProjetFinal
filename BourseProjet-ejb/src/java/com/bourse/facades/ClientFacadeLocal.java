package com.bourse.facades;

import com.bourse.entities.Client;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClientFacadeLocal {

    void create(Client client);

    void edit(Client client);

    void remove(Client client);

    Client find(Object id);

    List<Client> findAll();

    List<Client> findRange(int[] range);

    int count();

    List<Client> getListeClients();
    
    Client rechercherClientParID(Long idClient);

    void archiverClient(Client client);
    
}
