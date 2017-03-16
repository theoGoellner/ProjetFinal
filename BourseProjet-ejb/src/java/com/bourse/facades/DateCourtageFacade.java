package com.bourse.facades;

import com.bourse.entities.DateCourtage;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DateCourtageFacade extends AbstractFacade<DateCourtage> implements DateCourtageFacadeLocal {

    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DateCourtageFacade() {
        super(DateCourtage.class);
    }
    
}
