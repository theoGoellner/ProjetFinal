package com.bourse.controller;

import com.bourse.entities.Employe;
import com.bourse.entities.Identification;
import com.bourse.sessions.AdministrationSessionLocal;
import com.bourse.sessions.CommunSessionLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "controllerFrontOffice", urlPatterns = {"/controllerFrontOffice"})
public class controllerFrontOffice extends HttpServlet {

    @EJB
    private CommunSessionLocal communSession;

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
        
        String act;
        session= request.getSession(false);
        
        if (session==null) {
            act = "deconnexion";
        } else {
            act = request.getParameter("action");
        }
        
        if ((act == null) || (act.equals("null"))) {
            request.setAttribute("message", message);
            jspClient = "/FrontOffice/Accueil.jsp";
        } else {
            switch (act) {
                case "accueil":
                    jspClient = "/FrontOffice/Accueil.jsp";
                    break;   
                case "deconnexion":
                    jspClient = "/Authentification.jsp";
                    message = "votre session est expirée";
                    request.setAttribute("message", message);
                    break;
                    
                case "formInitPwd":
                    request.setAttribute("message", message);
                    jspClient = "/CommunOffice/InitialisationPwd.jsp";
                    break;
                case "pwdInit":
                    doActionInitialisationPwd(request, response);
                    break;                    
                    
                case "propositionCour":
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
    
}
