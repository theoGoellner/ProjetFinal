package com.bourse.sessions;

import com.bourse.entities.Identification;
import javax.ejb.Local;


@Local
public interface CommunSessionLocal {
    
    String stringHash(String s);

    boolean compareHashString(String s1, String s2);

    String URLEncode(String s);

    String URLDecode(String s);
    
    Identification rechercheIdentParLogin(String login);

    void modificationIdentification(Identification identification, String login, String pwd);
}
