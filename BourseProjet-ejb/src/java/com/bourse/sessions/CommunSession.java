package com.bourse.sessions;

import com.bourse.entities.Client;
import com.bourse.entities.Contenu;
import com.bourse.entities.Courtage;
import com.bourse.entities.Identification;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import com.bourse.facades.ContenuFacadeLocal;
import com.bourse.facades.CourtageFacadeLocal;
import com.bourse.facades.IdentificationFacadeLocal;
import com.bourse.facades.OperationFacadeLocal;
import com.bourse.facades.PorteFeuilleFacadeLocal;
import com.bourse.facades.TitreFacadeLocal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

@Stateless
public class CommunSession implements CommunSessionLocal {

    @EJB
    private OperationFacadeLocal operationFacade;
 
    @EJB
    private CourtageFacadeLocal courtageFacade;
    
    @EJB
    private TitreFacadeLocal titreFacade;

    @EJB
    private ContenuFacadeLocal contenuFacade;

    @EJB
    private PorteFeuilleFacadeLocal porteFeuilleFacade;

    @EJB
    private IdentificationFacadeLocal identificationFacade;
    
    @Override
    public List<Courtage> getListeCourageActuels() {        
        return courtageFacade.getListeCourtageActuels();
    }

    public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890&~#|`-_)('/?,;:.";
        StringBuffer pass = new StringBuffer();
        for (int x = 0; x < length; x++) {
            int i = (int) Math.floor(Math.random() * (chars.length() - 1));
            pass.append(chars.charAt(i));
        }
        return pass.toString();
    }

    @Override
    public String stringHash(String s) {
        try {
            byte[] bytesOfMessage = s.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] thedigest = md.digest(bytesOfMessage);
            BigInteger bigInt = new BigInteger(1, thedigest);
            String hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean compareHashString(String s1, String s2) {
        return (stringHash(s1).equals(s2));
    }

    @Override
    public String URLEncode(String s) {
        return DatatypeConverter.printBase64Binary(s.getBytes());
    }

    @Override
    public String URLDecode(String s) {
        String decoded = new String(DatatypeConverter.parseBase64Binary(s));
        return decoded;
    }

    @Override
    public Identification rechercheIdentParLogin(String login) {
        return identificationFacade.rechercherIdentParLogin(login);
    }

    @Override
    public void modificationIdentification(Identification identification, String login, String pwd) {
        identificationFacade.modifierIdentification(identification, login, pwd);
    }

    @Override
    public List<PorteFeuille> getListePFParClient(Client client) {
        return porteFeuilleFacade.getListePFParClient(client);
    }

    @Override
    public void creationContenu(PorteFeuille portefeuille, Titre titre, int qte) {
        contenuFacade.creerContenu(portefeuille, titre, qte);
    }

    @Override
    public Contenu rechercherContenuParPFetTitre(PorteFeuille portefeuille, Titre titre) {
        return contenuFacade.rechercheContenuParPFetTitre(portefeuille, titre);
    }

    @Override
    public void creationOperation(Titre titre, PorteFeuille pfCible, PorteFeuille pfSource, Boolean origine, int quantite, Date dateLimite) {
        operationFacade.creerOperation(titre, pfCible, pfSource, origine, quantite, dateLimite);
    }

    @Override
    public Titre rechercheTiterParID(Long idTitre) {
        return titreFacade.rechercherTitreParID(idTitre);
    }

    @Override
    public Courtage rechercheCourActuelParID(Long idCour) {
        return courtageFacade.rechercherCourActuelParID(idCour);
    }
    
    
    
}
