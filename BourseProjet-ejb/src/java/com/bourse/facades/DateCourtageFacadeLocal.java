package com.bourse.facades;

import com.bourse.entities.DateCourtage;
import java.util.List;
import javax.ejb.Local;

@Local
public interface DateCourtageFacadeLocal {

    void create(DateCourtage dateCourtage);

    void edit(DateCourtage dateCourtage);

    void remove(DateCourtage dateCourtage);

    DateCourtage find(Object id);

    List<DateCourtage> findAll();

    List<DateCourtage> findRange(int[] range);

    int count();
    
}
