package com.bourse.facades;

import com.bourse.entities.Operation;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class OperationFacade extends AbstractFacade<Operation> implements OperationFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

    @Override
    public void creerOperation(Titre titre, PorteFeuille pfCible, PorteFeuille pfSource, Boolean origine, int quantite, Date dateLimite) {
        Operation op = new Operation();
        op.setLeTitre(titre);
        op.setPortefeuilleCible(pfCible);
        op.setPortefeuilleSource(pfSource);
        op.setOrigineClient(origine);
        op.setQuantite(quantite);
        op.setDateLimite(dateLimite);
        em.persist(op);
    }
    
    
}
