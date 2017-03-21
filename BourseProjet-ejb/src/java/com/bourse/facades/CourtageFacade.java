package com.bourse.facades;

import com.bourse.entities.Courtage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CourtageFacade extends AbstractFacade<Courtage> implements CourtageFacadeLocal {

    SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
    
    @PersistenceContext(unitName = "BourseProjet-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CourtageFacade() {
        super(Courtage.class);
    }

    @Override
    public List<Courtage> getListeCourtageActuels() {
        List<Courtage> listCours = null;
        try {
            Query req = em.createQuery("Select c from Courtage as c where (c.dateCourtage.dateCourtage >= :date1) and c.dateCourtage.dateCourtage < :date2");            
            req.setParameter("date1", (Date)sdf.parse("17 - 03 - 2017"));            
            req.setParameter("date2", (Date)sdf.parse("18 - 03 - 2017"));
            listCours = req.getResultList();
        } catch (Exception e) {
            listCours = null;
            System.out.println("Erreur dans la facade Courtage dans la méthode getListeCourtageActuels " + e.getMessage());
        }
        return listCours;
    }
    
    
}
