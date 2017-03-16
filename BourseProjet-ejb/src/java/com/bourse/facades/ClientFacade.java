package com.bourse.facades;

import com.bourse.entities.Client;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ClientFacade extends AbstractFacade<Client> implements ClientFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    @Override
    public List<Client> getListeClients() {
        return findAll();
    }


    @Override
    public Client rechercherClientParID(Long idClient) {
        Client cli = null;
        try {
            Query req = em.createQuery("Select c from Client as c where c.id = :idClient");
            req.setParameter("idClient", idClient);
            cli = (Client) req.getSingleResult();
        } catch (Exception e) {
            cli = null;
            System.out.println("Erreur dans la facade Client dans la méthode rechercherClientParID " + e.getMessage());
        }
        return cli;
    }  

    @Override
    public void archiverClient(Client client) {
        client.setDateArchivage(new Date());
        em.merge(client);
    }
    
    
    
    
}
