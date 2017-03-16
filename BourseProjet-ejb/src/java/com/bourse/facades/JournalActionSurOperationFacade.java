package com.bourse.facades;

import com.bourse.entities.JournalActionSurOperation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class JournalActionSurOperationFacade extends AbstractFacade<JournalActionSurOperation> implements JournalActionSurOperationFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public JournalActionSurOperationFacade() {
        super(JournalActionSurOperation.class);
    }
    
}
