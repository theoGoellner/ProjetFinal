package com.bourse.sessions;

import com.bourse.facades.JournalConnexionFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class FrontOfficeSession implements FrontOfficeSessionLocal {

    @EJB
    private JournalConnexionFacadeLocal journalConnexionFacade;

  
}
