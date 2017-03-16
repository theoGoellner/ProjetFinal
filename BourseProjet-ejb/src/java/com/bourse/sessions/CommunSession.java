package com.bourse.sessions;

import com.bourse.entities.Identification;
import com.bourse.facades.IdentificationFacadeLocal;
import java.math.BigInteger;
import java.security.MessageDigest;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.bind.DatatypeConverter;

@Stateless
public class CommunSession implements CommunSessionLocal {

    @EJB
    private IdentificationFacadeLocal identificationFacade;

    
    public static String generate(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890&~#|`-_)('/?,;:."; 
        StringBuffer pass = new StringBuffer();
        for(int x=0;x<length;x++)   {
           int i = (int)Math.floor(Math.random() * (chars.length() -1));
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
    
}
