package com.bourse.facades;

import com.bourse.entities.Identification;
import com.bourse.entities.JournalConnexion;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JournalConnexionFacade extends AbstractFacade<JournalConnexion> implements JournalConnexionFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JournalConnexionFacade() {
        super(JournalConnexion.class);
    }

    @Override
    public void ajouterConnexion(Identification identification) {
        JournalConnexion Jcon=new JournalConnexion();
        Jcon.setIdentification(identification);
        Jcon.setDateConnexion(new Date());
        em.persist(Jcon);
    }
}
