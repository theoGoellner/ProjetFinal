package com.bourse.controller;

import com.bourse.entities.Client;
import com.bourse.entities.Courtage;
import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.entities.Identification;
import com.bourse.entities.Particulier;
import com.bourse.entities.PorteFeuille;
import com.bourse.enumeration.EnumFormEntreprise;
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

@WebServlet(name = "controllerCommun", urlPatterns = {"/controllerCommun"})
public class controllerCommun extends HttpServlet {

    @EJB
    private BackOfficeSessionLocal backOfficeSession;

    @EJB
    private CommunSessionLocal communSession;

    @EJB
    private AdministrationSessionLocal administrationSession;

    private String jspClient;
    private String message = "";
    
    private static final int DUREESESSIONVALIDE = 300;
    private static final int NBR_TENTATIVES_MAX = 3;
    
    private Identification ident = null;
    private HttpSession session;
    
    private Client cli = null;
    private PorteFeuille pf = null;    
    private List<Courtage> listeCours = null;
    private List<Particulier> listeParticulier = null;
    private List<Entreprise> listeEntreprise = null;

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
            request.setAttribute("message", message);
            jspClient = "/Accueil.jsp";
        } else {
            switch (act) {
                case "accueil":
                    request.setAttribute("message", message);
                    jspClient = "/Accueil.jsp";
                    break;
                case "formAuthentification":                    
                    request.setAttribute("message", message);
                    jspClient = "/Authentification.jsp";
                    break;
                case "authentification":
                    doActionAuthentification(request, response);
                    break;
                case "accueilBackOffice":
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/Accueil.jsp";
                    break;
                case "accueilFrontOffice":
                    request.setAttribute("message", message);
                    jspClient = "/FrontOffice/Accueil.jsp";
                    break;
                case "deconnexion":
                    request.setAttribute("message", message);
                    jspClient = "/Accueil.jsp";
                    session = request.getSession();
                    session.invalidate();
                    break;
                case "formInitPwd":
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/InitialisationPwd.jsp";
                    break;
                case "pwdInit":
                    doActionInitialisationPwd(request, response);
                    break;
                 
                // <editor-fold defaultstate="collapsed" desc="FRONT OFFICE">    
                    
                case "afficherPortefeuillesClient":
                    ident = (Identification) session.getAttribute("identification");            
                    if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                        cli = backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                        request.setAttribute("client", cli);
                    }
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/GestionDesPortefeuilles/afficherPortefeuillesClient.jsp";
                    break;
                case "afficherDetailPF":
                    pf = backOfficeSession.recherchePorteFeuilleParID(Long.valueOf(request.getParameter("idPF")));
                    request.setAttribute("portefeuille", pf);
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/GestionDesPortefeuilles/afficherDetailPF.jsp";
                    break;
                    
                case "selectionTitres":
                    listeCours = communSession.getListeCourageActuels();
                    request.setAttribute("listeCours", listeCours);
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/GestionDesOperations/selectionTitres.jsp";    
                    break;
                    
                case "historiqueVersementsClient":
                    ident = (Identification) session.getAttribute("identification");
                    if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                        session.setAttribute("clientSelectionne", (Client)backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient"))));
                    }
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/GestionDesVersements/historiqueVersements.jsp";
                    break;
                    
                case "formModifierClient":
                    ident = (Identification) session.getAttribute("identification");
                    if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                        cli = (Client)backOfficeSession.rechercheClientParID(Long.valueOf(request.getParameter("idClient")));
                        request.setAttribute("client", cli);
                    }                  
                    jspClient = "/CommunOffice/GestionDesClients/formModifClient.jsp";   
                    break;                    
                case "modifierParticulier":
                    System.out.println("cocoPar1");
                    doActionModifierParticulier(request, response);
                    System.out.println("cocoPar2");
                    break;
                case "modifierEntreprise":
                    System.out.println("cocoEntr1");
                    doActionModifierEntreprise(request, response);
                    System.out.println("cocoEntr2");
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

    // <editor-fold defaultstate="collapsed" desc="AUTHENTIFICATION">
    protected void doActionAuthentification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        String msgErreur = "Erreur d'authentification. Veuillez vérifier votre login ou mot de passe.";
        ident = administrationSession.rechercheIdentParLogin(login);
        if (ident != null) { // Login trouvé
            if (ident.isEstBloque()) {
                msgErreur = "Ce compte est bloqué. Veuillez contacter votre administrateur.";
            } else if (communSession.compareHashString(pwd, ident.getPwd())) { // Connexion réussie                
                session = request.getSession();
                //Long l= (new Date().getTime()-session.getCreationTime())/1000;
               
                session.setMaxInactiveInterval(DUREESESSIONVALIDE);
                session.setAttribute("identification", ident);
                session.setAttribute("nbrTentatives", 1);
                if (ident.getTypeUser().equalsIgnoreCase("Employe")) {
                    Employe user = administrationSession.rechercheEmployeParID(ident.getIdUser());
                    session.setAttribute("employe", user);

                    if (ident.getLesConnexions().isEmpty()) {
                        administrationSession.ajouterConnexion(ident);
                        jspClient = "/CommunOffice/InitialisationPwd.jsp";
                    } else {
                        administrationSession.ajouterConnexion(ident);
                        jspClient = "/BackOffice/Accueil.jsp";
                    }
                } else {
                    Client user = backOfficeSession.rechercheClientParID(ident.getIdUser());
                    session.setAttribute("client", user);
                    if (ident.getLesConnexions().isEmpty()) {
                        jspClient = "/CommunOffice/InitialisationPwd.jsp";
                        administrationSession.ajouterConnexion(ident);
                    } else {
                        administrationSession.ajouterConnexion(ident);
                        jspClient = "/FrontOffice/Accueil.jsp";
                    }
                }
            } else { // Mauvais mot de passe
                if (session == null) { // Si c'est la première tentative, on crée une session 
                    session = request.getSession();
                    session.setAttribute("identification", ident);
                    session.setAttribute("nbrTentatives", 1);
                } else { // Autres tentatives                
                    if (session.getAttribute("identification").equals(ident)) { // Si même session
                        if ((Integer)session.getAttribute("nbrTentatives") == (NBR_TENTATIVES_MAX - 1)) {
                            session.setAttribute("nbrTentatives", ((Integer) session.getAttribute("nbrTentatives")) + 1);
                            msgErreur = "Attention, il ne vous reste plus qu'une tentative d'authentification. Le compte sera ensuite bloqué.";
                        } else if ((Integer) session.getAttribute("nbrTentatives") == NBR_TENTATIVES_MAX) {// Si nbMax atteint, on bloque le compte (l'identification)                           
                            administrationSession.verrouillageIdentification(ident);
                            msgErreur = "Ce compte est désormais bloqué. Veuillez contacter votre administrateur pour le débloquer.";
                        } else { // Sinon, on incremente le compteur
                            session.setAttribute("nbrTentatives", ((Integer) session.getAttribute("nbrTentatives")) + 1);
                        }
                    } else { // Si session différente
                        session.setAttribute("identification", ident);
                        session.setAttribute("nbrTentatives", 1);
                    }
                }
                message = msgErreur;
                jspClient = "/Authentification.jsp";
            }
        } else { // Login non trouvé
            jspClient = "/Authentification.jsp";
            message = msgErreur;
        }
        request.setAttribute("message", message);
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
    
    protected void doActionModifierParticulier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nomClient = request.getParameter("nomClient");
        String prenomClient = request.getParameter("prenomClient");
        String dateNaisClient = request.getParameter("dateNaisClient");
        String lieuNaisClient = request.getParameter("lieuNaisClient");
        
        String tphClient = request.getParameter("tphClient");
        String emailClient = request.getParameter("emailClient");
        String adresseClient = request.getParameter("adresseClient");
                   
        ident = (Identification) session.getAttribute("identification");
        
        String idParticulier = request.getParameter("idClient");
        
        Particulier part = (Particulier) backOfficeSession.rechercheClientParID(Long.valueOf(idParticulier)); 
        if (((!part.getNom().equalsIgnoreCase(nomClient)) || (!part.getPrenom().equalsIgnoreCase(prenomClient))) && backOfficeSession.rechercheParticulierParNomPrenom(nomClient, prenomClient) != null) {
            message = "Erreur - Un client existe déja avec le même nom et le même prénom.";
        } else {
            if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                String niveauClient = request.getParameter("niveauClient");
                backOfficeSession.modificationParticulier(part, nomClient, prenomClient, part.getDateNais(), lieuNaisClient, 
                    tphClient, emailClient, adresseClient, Integer.parseInt(niveauClient));
            } else {                
                backOfficeSession.modificationParticulier(part, nomClient, prenomClient, part.getDateNais(), lieuNaisClient, 
                tphClient, emailClient, adresseClient, part.getNiveau());
            }         
            message = "Modification réussie !";
        }       
        request.setAttribute("message", message);
        
        
        if (ident.getTypeUser().equalsIgnoreCase("employe")) {
            session = request.getSession(true); 
                    listeParticulier = backOfficeSession.getListeParticuliersActifsParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesParticuliers", listeParticulier);
                    listeEntreprise = backOfficeSession.getListeEntreprisesActivesParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesEntreprises", listeEntreprise);
                    request.setAttribute("message", message);
                    jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
        } else if (ident.getTypeUser().equalsIgnoreCase("client")) {
            request.setAttribute("message", message);
            jspClient = "/FrontOffice/Accueil.jsp";    
        } 
        
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
                
        String idEntreprise = request.getParameter("idClient");
        
        Entreprise entr = (Entreprise) backOfficeSession.rechercheClientParID(Long.valueOf(idEntreprise));
        ident = (Identification) session.getAttribute("identification");
        
        if ((!entr.getSiret().equalsIgnoreCase(siret)) && (!entr.getNomEntreprise().equalsIgnoreCase(nomEntreprise)) && backOfficeSession.rechercheEntrepriseParSIRET(siret) != null) {
            message = "Erreur - Un client de type entreprise existe déja avec le même siret.";
        } else {
            if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                String niveauClient = request.getParameter("niveauClient");
            backOfficeSession.modificationEntreprise(entr, siret, nomEntreprise, EnumFormEntreprise.valueOf(formeEntreprise), contactEntreprise, tphContactEntreprise, 
                tphClient, emailClient, adresseClient, Integer.parseInt(niveauClient));
            } else {
                backOfficeSession.modificationEntreprise(entr, siret, nomEntreprise, EnumFormEntreprise.valueOf(formeEntreprise), contactEntreprise, tphContactEntreprise, 
                tphClient, emailClient, adresseClient, entr.getNiveau());
            }
            message = "Modification réussie !";            
        }   
        
        
        request.setAttribute("message", message);
        
        if (ident.getTypeUser().equalsIgnoreCase("employe")) {
            listeParticulier = backOfficeSession.getListeParticuliersActifsParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesParticuliers", listeParticulier);
                    listeEntreprise = backOfficeSession.getListeEntreprisesActivesParCourtier((Employe) session.getAttribute("employe"));
                    request.setAttribute("ListeDesEntreprises", listeEntreprise);
                    request.setAttribute("message", message);
            jspClient = "/BackOffice/GestionDesClients/gestionClientsCourtier.jsp";
        } else if (ident.getTypeUser().equalsIgnoreCase("client")) {
            request.setAttribute("message", message);
            jspClient = "/FrontOffice/Accueil.jsp";    
        }
    }
}
