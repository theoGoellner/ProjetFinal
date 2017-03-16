package com.bourse.controller;

import com.bourse.entities.Client;
import com.bourse.entities.Employe;
import com.bourse.entities.Identification;
import com.bourse.sessions.AdministrationSessionLocal;
import com.bourse.sessions.BackOfficeSessionLocal;
import com.bourse.sessions.CommunSessionLocal;
import com.bourse.sessions.FrontOfficeSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
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
    
    

    private static final int DUREESESSIONVALIDE = 300;
    private static final int NBR_TENTATIVES_MAX = 3;
    private String jspClient;
    private String message = "";
    private HttpSession session;
    private Identification ident = null;

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
            jspClient = "/Accueil.jsp";
        } else {
            switch (act) {
                case "accueil":
                    jspClient = "/Accueil.jsp";
                    break;
                case "formAuthentification":
                    jspClient = "/Authentification.jsp";
                    request.setAttribute("message", message);
                    break;
                case "authentification":
                    doActionAuthentification(request, response);
                    break;
                case "accueilBackOffice":
                    jspClient = "/BackOffice/Accueil.jsp";
                    break;
                case "accueilFrontOffice":
                    jspClient = "/FrontOffice/Accueil.jsp";
                    break;
                case "deconnexion":
                    jspClient = "/Accueil.jsp";
                    session = request.getSession(false);
                    session.invalidate();
                    break;
                case "formInitPwd":
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/InitialisationPwd.jsp";
                    break;

                case "pwdInit":
                    doActionInitialisationPwd(request, response);
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

    // ----------------------- GESTION DES IDENTIFICATIONS ---------------------
    protected void doActionAuthentification(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        String msgErreur = "Erreur d'authentification. Veuillez vérifier votre login et votre mot de passe.";

        ident = administrationSession.rechercheIdentParLogin(login);
        if (ident != null) { // Login trouvé
            if (ident.isEstBloque()) {
                msgErreur = "Ce compte est bloqué. Veuillez contacter votre administrateur.";
            } else if (communSession.compareHashString(pwd, ident.getPwd())) { // Connexion réussie
                session = request.getSession(true);
                session.setMaxInactiveInterval(DUREESESSIONVALIDE);
                session.setAttribute("identification", ident);
                administrationSession.ajouterConnexion(ident);
                if (ident.getTypeUser().equalsIgnoreCase("Employe")) { 
                    jspClient = "/BackOffice/Accueil.jsp";
                    Employe user = administrationSession.rechercheEmployeParID(ident.getIdUser());
                    session.setAttribute("employe", user); 
                } else {              
                    jspClient = "/FrontOffice/Accueil.jsp";
                    Client user = backOfficeSession.rechercheClientParID(ident.getIdUser());
                    session.setAttribute("client", user);
                }
            } else { // Mauvais mot de passe
                if (session == null) { // Si c'est la première tentative, on crée une session 
                    session = request.getSession(true);
                    session.setAttribute("identification", ident);
                    session.setAttribute("nbrTentatives", 1);
                } else { // Autres tentatives                
                    if (session.getAttribute("identification").equals(ident)) { // Si même session
                        if ((Integer) session.getAttribute("nbrTentatives") == (NBR_TENTATIVES_MAX - 1)) {
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

        protected void doActionInitialisationPwd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String loginUser = request.getParameter("loginUser");
        String pwd = request.getParameter("pwd");
        String newPwd = request.getParameter("newPwd");
        String newPwdConfirm = request.getParameter("newPwdConfirm");

        session = request.getSession(true);
        ident = (Identification)session.getAttribute("identification");
        
        if (!ident.getLogin().equalsIgnoreCase(loginUser)&&communSession.rechercheIdentParLogin(loginUser)!=null) {
            message = "Erreur : Ce login est déjà pris, veillez choisir un autre login.";
        }
        else {
            communSession.modificationIdentification(ident, loginUser, communSession.stringHash(newPwd));
            message = "Modification réussie.";
        }     
        jspClient = "/CommunOffice/InitialisationPwd.jsp";

        request.setAttribute("message", message);
    }
}
