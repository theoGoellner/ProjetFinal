package com.bourse.controller;

import com.bourse.entities.Client;
import com.bourse.entities.Courtage;
import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.entities.Identification;
import com.bourse.entities.Particulier;
import com.bourse.entities.PorteFeuille;
import com.bourse.entities.Titre;
import com.bourse.enumeration.*;
import com.bourse.sessions.*;
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
    private String typeRechClient = "";
    
    private Client cli = null;
    private Entreprise entr = null;
    private Particulier part = null;
    private Employe emp = null;
    private PorteFeuille pf = null;
    private Titre titre = null;
    private Courtage cour = null;
    private List<Employe> listeEmp = null;
    private List<Particulier> listeParticulier = null;
    private List<Entreprise> listeEntreprise = null;
    private List<PorteFeuille> listePF = null;
    private List<Courtage> listeCours = null;
    
    private Identification ident = null;
    private HttpSession session;
   
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

        String act;
        session= request.getSession(false);
        
        if (session==null) {
            act = "deconnexion";
        } else {
            act = request.getParameter("action");
        }
                
        if ((act == null) || (act.equals("null"))) {
            request.setAttribute("message", message);
            jspClient = "/BackOffice/Accueil.jsp";
        } else {
            switch (act) {
                case "accueil":
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/Accueil.jsp";
                    break;                  
                case "formInitPwd":
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/InitialisationPwd.jsp";
                    break;
                case "pwdInit":
                    doActionInitialisationPwd(request, response);
                    break;                
                case "deconnexion":
                    jspClient = "/Authentification.jsp";
                    message = "Votre session a expiré. Veuillez vous reconnecter.";
                    request.setAttribute("message", message);
                    break;    
                
                // <editor-fold defaultstate="collapsed" desc="GESTION DES EMPLOYES">
                case "formAjoutEmploye":
                    listeEmp = administrationSession.getListeEmployesActifs();
                    request.setAttribute("ListeDesEmployes", listeEmp);
                    request.setAttribute("message", message);
                    jspClient = "/Administration/GestionDesEmployes/formAjoutEmploye.jsp";
                    break;
                case "ajoutEmploye":
                    doActionAjoutEmploye(request, response);
                    break;
                case "formModifierEmploye":                   
                    emp = administrationSession.rechercheEmployeParID(Long.valueOf(request.getParameter("idEmploye")));
                    jspClient = "/Administration/GestionDesEmployes/formModifEmploye.jsp";
                    request.setAttribute("employe", emp);                    
                    break;                    
                case "modifierEmploye":
                    doActionModifierEmploye(request, response);
                    break;
                case "archiverEmploye":
                    doActionArchiverEmploye(request, response);
                    break; 
                // </editor-fold>
                    
                // <editor-fold defaultstate="collapsed" desc="GESTION DES CLIENTS">
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
                case "archiverClientAjout":   
                    jspClient = "/BackOffice/GestionDesClients/formAjoutClient.jsp";
                    doActionArchiverClient(request, response);                    
                    break; 
                case "archiverClientGestion":
                    jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
                    doActionArchiverClient(request, response);
                    break; 
                case "formModifierClient":                                       
                    jspClient = "/CommunOffice/GestionDesClients/formModifClient.jsp";
                    request.setAttribute("client", (Client)backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient"))));
                    break;                    
                case "modifierParticulier":
                    doActionModifierParticulier(request, response);
                    break;
                case "modifierEntreprise":
                    doActionModifierEntreprise(request, response);
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
                case "formRechClientGestion":
                    typeRechClient = "gestion";
                    session.setAttribute("typeRechClient", typeRechClient);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/RechercheClient/formRechClient.jsp";
                    break;
                case "formRechClientPF":
                    typeRechClient = "PF";
                    session.setAttribute("typeRechClient", typeRechClient);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/RechercheClient/formRechClient.jsp";
                    break;
                case "formRechClientVersement":
                    typeRechClient = "versement";
                    session.setAttribute("typeRechClient", typeRechClient);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/RechercheClient/formRechClient.jsp";
                    break;
                case "rechClient":
                    doActionRechercherClient(request, response);
                    break;
                // </editor-fold>
                    
                // <editor-fold defaultstate="collapsed" desc="GESTION DES CONTRATS/PORTEFEUILLES">                
                case "formAjoutContrat":
                    cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));                    
                    request.setAttribute("client", cli);
                    request.setAttribute("message", message); 
                    jspClient = "/BackOffice/GestionDesContrats/formAjoutContrat.jsp";
                    break;
                case "ajoutContrat":
                    doActionAjoutContrat(request, response);
                    break;
                case "gestionContratsClient":
                    cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                    request.setAttribute("client", cli);
                    request.setAttribute("message", message);                    
                    jspClient = "/BackOffice/GestionDesContrats/gestionContratsClient.jsp";
                    break;    
                case "formModifContrat":
                    jspClient = "/BackOffice/GestionDesContrats/formModifContrat.jsp";
                    break;  
                // </editor-fold>
                                        
                // <editor-fold defaultstate="collapsed" desc="GESTION DES VERSEMENTS">
                case "formGestionVersements":
                    session.setAttribute("clientSelectionne", (Client)backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient"))));
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesVersements/formGestionVersements.jsp";
                    break;
                case "validerVersement":
                    doActionValiderVersement(request, response);
                    break;
                // </editor-fold>
                
                // <editor-fold defaultstate="collapsed" desc="GESTION DES TITRE et COURTAGE">    
                case "propositionCour":
                    cour = communSession.rechercheCourActuelParID(Long.valueOf(request.getParameter("idCour")));
                    request.setAttribute("cour", cour);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesOperations/propositionOperation.jsp";
                    break;   
                case "achatCour":                    
                    break;
                // </editor-fold>
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

    
    // <editor-fold defaultstate="collapsed" desc="GESTION DES EMPLOYES">    
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
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="GESTION DES CLIENTS">
    
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
        
        String idParticulier = request.getParameter("idClient");
        
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
        jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
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
        
        String idEntreprise = request.getParameter("idClient");
        
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
        jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
    }
        
    protected void doActionRechercherClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        listeParticulier = null;
        listeEntreprise = null;

        String typeClient = request.getParameter("typeClient");
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String siret = request.getParameter("siret");
        String nomEntreprise = request.getParameter("nomEntreprise");
       
        Employe user = (Employe) session.getAttribute("employe");
        
        if (typeClient.equalsIgnoreCase("Particulier")) {
            listeParticulier = backOfficeSession.rechercheListeParticuliersParCourtierParNomPrenom(user, nomClient, prenomClient);
            if (listeParticulier.isEmpty()) {
                message = "Aucun client ne correspond aux critère de recherche.";
                jspClient = "/BackOffice/GestionDesClients/RechercheClient/formRechClient.jsp";
            } else {
                request.setAttribute("ListeDesParticuliers", listeParticulier);
                message = "Vous avez " + listeParticulier.size() + " client(s) qui repond(ent) aux critères de recherche.";
                jspClient = "/BackOffice/GestionDesClients/RechercheClient/resultRechParticulier.jsp";
            }
        } else {
            listeEntreprise = backOfficeSession.rechercheListeEntreprisesParCourtierParNomPrenom(user, siret, nomEntreprise);
            if (listeEntreprise.isEmpty()) {
                message = " Aucun client ne correspond aux critère de recherche.";
                jspClient = "/BackOffice/GestionDesClients/RechercheClient/formRechClient.jsp";
            } else {
                request.setAttribute("ListeDesEntreprises", listeEntreprise);
                message = " Vous avez " + listeEntreprise.size() + " client(s) qui repond(ent) aux critères de recherche.";
                jspClient = "/BackOffice/GestionDesClients/RechercheClient/resultRechEntreprise.jsp";
            }
        }
        request.setAttribute("message", message);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="GESTION DES CONTRATS/PORTEFEUILLES">
    
    protected void doActionAjoutContrat(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String typePorteFeuille = request.getParameter("typePorteFeuille");
        String dateDebutContrat = request.getParameter("dateDebutContrat");
        String ribContrat = request.getParameter("ribContrat");
        String typeContrat = request.getParameter("typeContrat");
        
        String montantInitialPF = request.getParameter("montantInitialPF");
        
        String typePFClassique = request.getParameter("typePFClassique");
        String niveauGestionClassique = request.getParameter("niveauGestionClassique");
        String nomChargeCompte = request.getParameter("nomChargeCompte");
        String valeurMaxClassique = request.getParameter("valeurMaxClassique");
        String pourcMaxClassique = request.getParameter("pourcMaxClassique");
        
        String dateOuverturePEA = request.getParameter("dateOuverturePEA");
        
        String dateOuverturePEP = request.getParameter("dateOuverturePEP");
        String dateFermeturePEP = request.getParameter("dateFermeturePEP");  
        
        cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));

        session = request.getSession(true);
        
        if (typePorteFeuille.equalsIgnoreCase("Classique")) {            
            backOfficeSession.creationClassique(EnumTypeGestCompteClassique.valueOf(typePFClassique), EnumNiveauGestionCompteClassique.valueOf(niveauGestionClassique), 
                    nomChargeCompte, Double.valueOf(valeurMaxClassique), Double.valueOf(pourcMaxClassique), Double.valueOf(montantInitialPF), 
                    backOfficeSession.creationContrat(Date.valueOf(dateDebutContrat), ribContrat, typeContrat, cli)); 
            message = "Ajout d'un contrat de type Classique réussi !";                                 
        } else if (typePorteFeuille.equalsIgnoreCase("PEA")) {
            backOfficeSession.creationPEA(Date.valueOf(dateOuverturePEA), Double.valueOf(montantInitialPF), 
                    backOfficeSession.creationContrat(Date.valueOf(dateDebutContrat), ribContrat, typeContrat, cli));
            message = "Ajout d'un contrat de type PEA réussi !";
        } else {
            backOfficeSession.creationPERP(Date.valueOf(dateOuverturePEP), Date.valueOf(dateFermeturePEP), Double.valueOf(montantInitialPF), 
                    backOfficeSession.creationContrat(Date.valueOf(dateDebutContrat), ribContrat, typeContrat, cli));
            message = "Ajout d'un contrat de type PEP-PERP réussi !";
        }
        request.setAttribute("client", cli);
        request.setAttribute("message", message);
        jspClient = "/CommunOffice/GestionDesPortefeuilles/afficherPortefeuillesClient.jsp";
    }    
    // </editor-fold>
         
    // <editor-fold defaultstate="collapsed" desc="INITIALISATION MOT DE PASSE">
    protected void doActionInitialisationPwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginUser = request.getParameter("loginUser");
        String newPwd = request.getParameter("newPwd");

        session = request.getSession(true);
        ident = (Identification) session.getAttribute("identification");

        if (!ident.getLogin().equalsIgnoreCase(loginUser) && communSession.rechercheIdentParLogin(loginUser) != null) {
            message = "Erreur : Ce login est déjà pris, veillez choisir un autre login.";
            jspClient = "/CommunOffice/InitialisationPwd.jsp";
        } else {
            communSession.modificationIdentification(ident, loginUser, communSession.stringHash(newPwd));
            message = "Modification réussie !";
            if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                jspClient = "/BackOffice/Accueil.jsp";
            } else {
                jspClient = "/FrontOffice/Accueil.jsp";
            }
        }
        request.setAttribute("message", message);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="GESTION DES VERSEMENTS">
    protected void doActionValiderVersement(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session = request.getSession(true);
        cli = (Client)session.getAttribute("clientSelectionne");
        pf = backOfficeSession.recherchePorteFeuilleParID(Long.valueOf(request.getParameter("idPF")));
        Double montantVersement = Double.valueOf(request.getParameter("montantVersement"));
        
        backOfficeSession.creationVersement(cli, pf, montantVersement);
        
        message = "Versement réussi !";
        request.setAttribute("message", message);
        jspClient = "/BackOffice/GestionDesVersements/formGestionVersements.jsp";
    }       
    // </editor-fold>
}
