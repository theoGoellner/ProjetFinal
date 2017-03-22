package com.bourse.sessions;

import com.bourse.entities.Client;
import com.bourse.entities.Contenu;
import com.bourse.entities.Courtage;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Identification;
import com.bourse.entities.Titre;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;


@Local
public interface CommunSessionLocal {
    
    String stringHash(String s);

    boolean compareHashString(String s1, String s2);

    String URLEncode(String s);

    String URLDecode(String s);
    
    Identification rechercheIdentParLogin(String login);

    void modificationIdentification(Identification identification, String login, String pwd);

    List<PorteFeuille> getListePFParClient(Client client);

    void creationContenu(PorteFeuille portefeuille, Titre titre, int qte);

    Contenu rechercherContenuParPFetTitre(PorteFeuille portefeuille, Titre titre);
    
    List<Courtage> getListeCourageActuels();

    void creationOperation(Titre titre, PorteFeuille pfCible, PorteFeuille pfSource, Boolean origine, int quantite, Date dateLimite);

    Titre rechercheTiterParID(Long idTitre);

    Courtage rechercheCourActuelParID(Long idCour);
}
