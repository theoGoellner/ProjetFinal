package com.bourse.facades;

import com.bourse.entities.Identification;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IdentificationFacadeLocal {

    void create(Identification identification);

    void edit(Identification identification);

    void remove(Identification identification);

    Identification find(Object id);

    List<Identification> findAll();

    List<Identification> findRange(int[] range);

    int count();
    
    void creerIdentification(String login, String pwd, String typeUser, Long idUser);

    void modifierIdentification(Identification ident, String login, String pwd);

    void supprimerIdentification(Identification ident);

    Identification rechercherLoginUser(String login, String typeUser);

    Identification rechercherIdentParIDUserEtType(Long idUser, String typeUser);

    void archiverIdentification(Identification ident);

    Identification rechercherIdentParLogin(String login);

    void verrouillerIdentification(Identification ident);
}
