package com.bourse.facades;

import com.bourse.entities.Classique;
import com.bourse.entities.Contrat;
import com.bourse.enumeration.EnumNiveauGestionCompteCalssique;
import com.bourse.enumeration.EnumTypeGestCompteClassique;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ClassiqueFacade extends AbstractFacade<Classique> implements ClassiqueFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassiqueFacade() {
        super(Classique.class);
    }   

    @Override
    public Classique creerClassique(EnumTypeGestCompteClassique typeClassique, EnumNiveauGestionCompteCalssique niveauGestion, 
            String nomCharge, Double valeurMax, Double pourcMax, Double montantInitial, Contrat contrat) {
        Classique classiq = new Classique();
        classiq.setType(typeClassique);
        classiq.setNiveauGestion(niveauGestion);
        classiq.setChargeCompte(nomCharge);
        classiq.setValeurMax(valeurMax);
        classiq.setPourcentageMax(pourcMax);
        classiq.setMontantInitial(montantInitial);
        classiq.setLeContrat(contrat);
        em.persist(classiq);
        return classiq;
    }
    
    
}
