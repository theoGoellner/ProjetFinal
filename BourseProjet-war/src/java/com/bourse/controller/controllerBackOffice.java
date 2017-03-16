package com.bourse.controller;

import com.bourse.entities.Client;
import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.entities.Identification;
import com.bourse.entities.Particulier;
import com.bourse.enumeration.EnumFormEntreprise;
import com.bourse.enumeration.EnumRoleEmploye;
import com.bourse.sessions.AdministrationSessionLocal;
import com.bourse.sessions.BackOfficeSessionLocal;
import com.bourse.sessions.CommunSessionLocal;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controllerBackOffice", urlPatterns = {"/controllerBackOffice"})
public class controllerBackOffice extends HttpServlet {

    @EJB
    private BackOfficeSessionLocal backOfficeSession;
  
    @EJB
    private CommunSessionLocal communSession;

    @EJB
    private AdministrationSessionLocal administrationSession;
    
    private String jspClient;
    private String message = "";
    private Client cli = null;
    private Entreprise entr = null;
    private Particulier part = null;
    private Employe emp = null;
    private List<Employe> listeEmp = null;
    private List<Particulier> listeParticulier = null;
    private List<Entreprise> listeEntreprise = null;
    private Identification ident = null;
    private HttpSession session;
    
    private String redirection;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        message = "";
        jspClient = null;
        String act = request.getParameter("action");
        if ((act == null) || (act.equals("null"))) {
            jspClient = "/BackOffice/Accueil.jsp";
        } else {
            switch (act) {
                case "accueil":
                    jspClient = "/BackOffice/Accueil.jsp";
                    break;   
                case "formAjoutEmploye":
                    listeEmp = administrationSession.getListeEmployesActifs();
                    request.setAttribute("ListeDesEmployes", listeEmp);
                    request.setAttribute("message", message);
                    jspClient = "/Administration/GestionDesEmployes/formAjoutEmploye.jsp";
                    break;
                case "ajoutEmploye":
                    doActionAjoutEmploye(request, response);
                    break;
                case "archiverEmploye":
                    doActionArchiverEmploye(request, response);
                    break; 
                case "formModifierEmploye":                   
                    emp = administrationSession.rechercheEmployeParID(Long.valueOf(request.getParameter("idEmploye")));
                    jspClient = "/Administration/GestionDesEmployes/formModifEmploye.jsp";
                    request.setAttribute("employe", emp);                    
                    break;                    
                case "modifierEmploye":
                    doActionModifierEmploye(request, response);
                    break;
                case "formAjoutClient":
                    listeParticulier = backOfficeSession.getListeParticuliersActifs();
                    request.setAttribute("ListeDesParticuliers", listeParticulier);
                    listeEntreprise = backOfficeSession.getListeEntreprisesActives();
                    request.setAttribute("ListeDesEntreprises", listeEntreprise);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
                    break;
                case "ajoutClient":
                    doActionAjoutClient(request, response);
                    break;
                case "gestionClientsCourtier":
                    session = request.getSession(true); 
                    listeParticulier = backOfficeSession.getListeParticuliersActifsParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesParticuliers", listeParticulier);
                    listeEntreprise = backOfficeSession.getListeEntreprisesActivesParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesEntreprises", listeEntreprise);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
                    break;
                case "gestionContratsClient":
                    System.out.println("toto" + request.getParameter("idClient"));
                    cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                    System.out.println("toto" + cli.getMail());
                    request.setAttribute("client", cli);
                    request.setAttribute("message", message);                    
                    jspClient = "/BackOffice/GestionDesClients/GestionDesContrats/gestionContratsClient.jsp";
                    break;
                case "formAjoutContrat":
                    cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                    request.setAttribute("client", cli);
                    request.setAttribute("message", message); 
                    jspClient = "/BackOffice/GestionDesClients/GestionDesContrats/formAjoutContrat.jsp";
                    break;
                case "formModifContrat":
                    jspClient = "/BackOffice/GestionDesClients/GestionDesContrats/formModifContrat.jsp";
                    break;
                case "archiverClientAjout":   
                    jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
                    doActionArchiverClient(request, response);                    
                    break; 
                case "archiverClientGestion":
                    jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
                    doActionArchiverClient(request, response);
                    break;                
                case "formModifierClient":
                    cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                    if (cli instanceof Entreprise){
                        jspClient = "/BackOffice/GestionDesClients/formModifEntreprise.jsp";
                        request.setAttribute("entreprise", (Entreprise)cli);
                    } else {
                        jspClient = "/BackOffice/GestionDesClients/formModifParticulier.jsp";
                        request.setAttribute("particulier", (Particulier)cli);
                    }
                    break;                    
                case "modifierParticulier":
                    doActionModifierParticulier(request, response);
                    break;
                case "modifierEntreprise":
                    doActionModifierEntreprise(request, response);
                    break;
                case "formRechClient":
                    request.setAttribute("message", message);
                    jspClient = "/Administration/GestionDesEmployes/formRechClient.jsp";
                    break;
                case "RechClient":
                    doActionRechercherClient(request, response);
                    break;
            }
        }
        RequestDispatcher Rd;
        Rd = getServletContext().getRequestDispatcher(jspClient);
        Rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    // ----------------------- GESTION DES EMPLOYES ----------------------------
    
    protected void doActionAjoutEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomEmploye = request.getParameter("nomEmploye");
        String prenomEmploye = request.getParameter("prenomEmploye");
        String dateEmbauche = request.getParameter("dateEmbauche");
        String emailEmploye = request.getParameter("emailEmploye");
        String niveauEmploye = request.getParameter("niveauEmploye");
        String fonctionEmploye = request.getParameter("fonctionEmploye");
        String idRespSection = request.getParameter("idRespSection");
        String idRespGroupe = request.getParameter("idRespGroupe");
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");

        Employe responsable = null;
        String typeUser = "Employe";

        if (administrationSession.rechercheEmployeParNomPrenom(nomEmploye, prenomEmploye) != null) {
            message = "Erreur - Un employé existe déja avec le même nom et le même prénom.";
        } else if (administrationSession.rechercheLoginUser(login, typeUser) != null) {
            message = "Erreur - Ce login est déja pris ! Veuillez en choisir un autre ! Soyez original !";
        } else if (fonctionEmploye.equalsIgnoreCase("Courtier")) {
            responsable = administrationSession.rechercheEmployeParID(Long.valueOf(idRespGroupe));
            administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser, 
                    administrationSession.creationEmploye(nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche),
                    Integer.valueOf(niveauEmploye), EnumRoleEmploye.valueOf(fonctionEmploye), responsable).getId());
            message = "Le courtier " + nomEmploye + " " + prenomEmploye + " a été crée avec succès !";
        } else if (fonctionEmploye.equalsIgnoreCase("ChefGroupe")) {
            responsable = administrationSession.rechercheEmployeParID(Long.valueOf(idRespSection));
            administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser, 
                    administrationSession.creationEmploye(nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche),
                    Integer.valueOf(-1), EnumRoleEmploye.valueOf(fonctionEmploye), responsable).getId());
            message = "Le chef de groupe " + nomEmploye + " " + prenomEmploye + " a été crée avec succès !";
        } else if (fonctionEmploye.equalsIgnoreCase("Administrateur")) {
            administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser, 
                    administrationSession.creationEmploye(nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche),
                    Integer.valueOf(-1), EnumRoleEmploye.valueOf(fonctionEmploye), null).getId());
            message = "Le " + fonctionEmploye + " " + nomEmploye + " " + prenomEmploye + " a été crée avec succès !";
        } else {
            responsable = administrationSession.rechercheChefSalle();
            administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser, 
                    administrationSession.creationEmploye(nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche),
                    Integer.valueOf(-1), EnumRoleEmploye.valueOf(fonctionEmploye), responsable).getId());
            message = "Le " + fonctionEmploye + " " + nomEmploye + " " + prenomEmploye + " a été crée avec succès !";
        }
        listeEmp = administrationSession.getListeEmployesActifs();
        request.setAttribute("ListeDesEmployes", listeEmp);
        request.setAttribute("message", message);
        jspClient = "/Administration/GestionDesEmployes/formAjoutEmploye.jsp";
    }

    protected void doActionArchiverEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        jspClient = "/Administration/GestionDesEmployes/formAjoutEmploye.jsp";
        emp = administrationSession.rechercheEmployeParID(Long.valueOf(request.getParameter("idEmploye")));
        administrationSession.archivageEmploye(emp);
        listeEmp = administrationSession.getListeEmployesActifs();
        request.setAttribute("ListeDesEmployes", listeEmp);
        message = "Archivage de l'employé réussi !";
        request.setAttribute("message", message);
    }
    
    protected void doActionModifierEmploye(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String nomEmploye = request.getParameter("nomEmploye");
        String prenomEmploye = request.getParameter("prenomEmploye");
        String dateEmbauche = request.getParameter("dateEmbauche");
        String emailEmploye = request.getParameter("emailEmploye");
        String niveauEmploye = request.getParameter("niveauEmploye");
        
        String idEmploye = request.getParameter("idEmploye");
        
        Employe emp = (Employe) administrationSession.rechercheEmployeParID(Long.valueOf(idEmploye));

        if ((!emp.getNom().equalsIgnoreCase(nomEmploye) || !emp.getPrenom().equalsIgnoreCase(prenomEmploye))
                && (administrationSession.rechercheEmployeParNomPrenom(nomEmploye, prenomEmploye) != null)) {
            message = "Erreur - Un employé existe déja avec le même nom et le même prénom.";
        } else {           
            if (emp.getRole().equals(EnumRoleEmploye.Courtier)) {
                administrationSession.modificationEmploye(emp, nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche), Integer.parseInt(niveauEmploye));
            } else {
                administrationSession.modificationEmploye(emp, nomEmploye, prenomEmploye, emailEmploye, Date.valueOf(dateEmbauche), Integer.valueOf(-1));
            }
            message = "Modification réussie.";
        }
            
        listeEmp = administrationSession.getListeEmployesActifs();
        request.setAttribute("ListeDesEmployes", listeEmp);
        request.setAttribute("message", message);
        jspClient = "/Administration/GestionDesEmployes/formAjoutEmploye.jsp";
    }
    
    // ------------------------ GESTION DES CLIENTS ----------------------------
    
    protected void doActionAjoutClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String typeClient = request.getParameter("typeClient");
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String dateNaisClient = request.getParameter("dateNaisClient");
        String lieuNaisClient = request.getParameter("lieuNaisClient");
        String siret = request.getParameter("siret");
        String nomEntreprise = request.getParameter("nomEntreprise");
        String formeEntreprise = request.getParameter("formeEntreprise");
        String contactEntreprise = request.getParameter("contactEntreprise");
        String tphContactEntreprise = request.getParameter("tphContactEntreprise");
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        
        String tphClient = request.getParameter("tphClient");
        String emailClient = request.getParameter("emailClient");
        String adresseClient = request.getParameter("adresseClient");
        String niveauClient = request.getParameter("niveauClient");
        
        String typeUser = "Client";
        
        session = request.getSession(true);
        Employe courtier = (Employe)session.getAttribute("employe");
        
        if (typeClient.equalsIgnoreCase("Particulier")) {
            if (backOfficeSession.rechercheParticulierParNomPrenom(nomClient, prenomClient) != null) {
                message = "Erreur - Un client de type particulier existe déja avec le même nom et le même prénom.";
            } else if (administrationSession.rechercheLoginUser(login, typeUser) != null) {
                message = "Erreur - Ce login est déja pris ! Veuillez en choisir un autre ! Soyez original !";
            } else {
                administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser, 
                        backOfficeSession.creationParticulier(nomClient, prenomClient, Date.valueOf(dateNaisClient), lieuNaisClient, 
                                tphClient, emailClient, adresseClient, Integer.valueOf(niveauClient), courtier).getId());
                message = "Le client de type " + typeClient + " " + prenomClient + " " + nomClient + " a été crée avec succès !";            
            }
        } else {
            if (backOfficeSession.rechercheEntrepriseParSIRET(siret) != null) {
                message = "Erreur - Un client de type entreprise existe déja avec le même siret.";
            } else if (administrationSession.rechercheLoginUser(login, typeUser) != null) {
                message = "Erreur - Ce login est déja pris ! Veuillez en choisir un autre ! Soyez original !";
            } else {
                administrationSession.creationIdentification(login, communSession.stringHash(pwd), typeUser,
                        backOfficeSession.creationEntreprise(siret, nomEntreprise, EnumFormEntreprise.valueOf(formeEntreprise), contactEntreprise, tphContactEntreprise, 
                                tphClient, emailClient, adresseClient, Integer.valueOf(niveauClient), courtier).getId());
                message = "Le client de type " + typeClient + " " + nomEntreprise + " a été crée avec succès !";  
            }
        }
        
        listeParticulier = backOfficeSession.getListeParticuliersActifs();
        request.setAttribute("ListeDesParticuliers", listeParticulier);
        listeEntreprise = backOfficeSession.getListeEntreprisesActives();
        request.setAttribute("ListeDesEntreprises", listeEntreprise);
        request.setAttribute("message", message);
        jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
    }
    
    protected void doActionArchiverClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {    
        
        cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
        backOfficeSession.archivageClient(cli);
        
        listeParticulier = backOfficeSession.getListeParticuliersActifsParCourtier((Employe) session.getAttribute("employe"));
        request.setAttribute("ListeDesParticuliers", listeParticulier);
        listeEntreprise = backOfficeSession.getListeEntreprisesActivesParCourtier((Employe) session.getAttribute("employe"));
        request.setAttribute("ListeDesEntreprises", listeEntreprise);
        message = "Archivage du client réussi !";
        request.setAttribute("message", message);
    }

    
    protected void doActionModifierParticulier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String dateNaisClient = request.getParameter("dateNaisClient");
        String lieuNaisClient = request.getParameter("lieuNaisClient");
        
        String tphClient = request.getParameter("tphClient");
        String emailClient = request.getParameter("emailClient");
        String adresseClient = request.getParameter("adresseClient");
        String niveauClient = request.getParameter("niveauClient");
        
        String idParticulier = request.getParameter("idParticulier");
        
        Particulier part = (Particulier) backOfficeSession.rechercheClientParID(Long.valueOf(idParticulier)); 
        
        if (backOfficeSession.rechercheParticulierParNomPrenom(nomClient, prenomClient) != null) {
            message = "Erreur - Un client de type particulier existe déja avec le même nom et le même prénom.";
        } else {
            backOfficeSession.modificationParticulier(part, nomClient, prenomClient, Date.valueOf(dateNaisClient), lieuNaisClient, 
                tphClient, emailClient, adresseClient, Integer.parseInt(niveauClient));
        }  
        
        listeParticulier = backOfficeSession.getListeParticuliersActifs();
        request.setAttribute("ListeDesParticuliers", listeParticulier);
        listeEntreprise = backOfficeSession.getListeEntreprisesActives();
        request.setAttribute("ListeDesEntreprises", listeEntreprise);
        message = "Modification du client réussi !";
        request.setAttribute("message", message);
        jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
    }
    
    protected void doActionModifierEntreprise(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String siret = request.getParameter("siret");
        String nomEntreprise = request.getParameter("nomEntreprise");
        String formeEntreprise = request.getParameter("formeEntreprise");
        String contactEntreprise = request.getParameter("contactEntreprise");
        String tphContactEntreprise = request.getParameter("tphContactEntreprise");
        
        String tphClient = request.getParameter("tphClient");
        String emailClient = request.getParameter("emailClient");
        String adresseClient = request.getParameter("adresseClient");
        String niveauClient = request.getParameter("niveauClient");
        
        String idEntreprise = request.getParameter("idEntreprise");
        
        Entreprise entr = (Entreprise) backOfficeSession.rechercheClientParID(Long.valueOf(idEntreprise));
        
        if (backOfficeSession.rechercheEntrepriseParSIRET(siret) != null) {
            message = "Erreur - Un client de type entreprise existe déja avec le même siret.";
        } else {
            backOfficeSession.modificationEntreprise(entr, siret, nomEntreprise, EnumFormEntreprise.valueOf(formeEntreprise), contactEntreprise, tphContactEntreprise, 
                tphClient, emailClient, adresseClient, Integer.parseInt(niveauClient));
        }   
        
        listeParticulier = backOfficeSession.getListeParticuliersActifs();
        request.setAttribute("ListeDesParticuliers", listeParticulier);
        listeEntreprise = backOfficeSession.getListeEntreprisesActives();
        request.setAttribute("ListeDesEntreprises", listeEntreprise);
        message = "Modification du client réussi !";
        request.setAttribute("message", message);
        jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
    }  
    
    
        protected void doActionRechercherClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listeParticulier =null;
        listeEntreprise =null;  
        
        String typeClient = request.getParameter("typeClient");
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String siret = request.getParameter("siret");
        String nomEntreprise = request.getParameter("nomEntreprise");
       
        session = request.getSession(true);
        Employe courtier = (Employe)session.getAttribute("employe");
        
       if (typeClient.equalsIgnoreCase("Particulier")) {
           listeParticulier=backOfficeSession.rechercheListeParticuliersParCourtierParNomPrenom(courtier, nomClient, prenomClient);
           if (listeParticulier.isEmpty()){
                    message = " Aucun client ne correspond aux critère de recherche ";
                    jspClient = "/Administration/GestionDesEmployes/formRechClient.jsp";
           } else {
                    message = " Vous avez "+listeParticulier.size()+" client qui repondent aux critères de recherche";
                    jspClient = "/Administration/GestionDesEmployes/resultRechClient.jsp";
           }
       } else {
       listeEntreprise=backOfficeSession.rechercheListeEntreprisesParCourtierParNomPrenom(courtier, siret, nomEntreprise);
           if (listeEntreprise.isEmpty()){
                    message = " Aucun client ne correspond aux critère de recherche ";
                    jspClient = "/Administration/GestionDesEmployes/formRechClient.jsp";
           } else {
                    message = " Vous avez "+listeEntreprise.size()+" client qui repondent aux critères de recherche";
                    jspClient = "/Administration/GestionDesEmployes/resultRechClient.jsp";
           }
       }
            request.setAttribute("ListeDesParticuliers", listeParticulier);
            request.setAttribute("ListeDesEntreprises", listeEntreprise);
            request.setAttribute("message", message);

    }
}
