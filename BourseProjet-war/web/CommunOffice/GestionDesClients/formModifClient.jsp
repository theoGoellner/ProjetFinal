
<%@page import="com.bourse.entities.Identification"%>
<%@page import="com.bourse.entities.Entreprise"%>
<%@page import="com.bourse.entities.Particulier"%>
<%@page import="com.bourse.entities.Client"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>

        <script src="Presentation/JS/bourse.js"></script>

        <jsp:useBean id="identification" scope="session" class="com.bourse.entities.Identification"></jsp:useBean>

            <title>Modifier Client</title>       
        </head>
        <body>
        <%  Identification ident = (Identification) session.getAttribute("identification");
            Client client = null;
            if (ident.getTypeUser().equalsIgnoreCase("employe")) {               
                client = (Client) request.getAttribute("client"); %> 
                <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
            <% } else { 
                client = (Client) session.getAttribute("client"); %> 
                <%@include  file="../../jsp_commun/menuFrontOffice.jsp" %>
            <% } %>
        <div class="container-fluid text-center col-lg-offset-2">
            <div class="row content">
                <div class="col-lg-10 text-left"> 
                    <div align="middle"> 
                        <img src="Presentation/Images/baniere.jpg">
                    </div>
                    <hr>
                    <% SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); %>
                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireModifClient" class="form-horizontal" role="form" method="get" action="controllerCommun">
                                <div class="modal-header">
                                    <h1 class="text-center">Modification d'un client existant</h1>
                                </div>
                                <div class="row"><p>     </p></div>  
                                <% if (client instanceof Particulier) {%>
                                <%-- Champs pour les objets Particulier (nom, prénom, date de naissance, lieu de naissance) --%>
                                <div class="form-group">
                                    <label for="nomClient" class="col-lg-4 control-label">Nom</label>
                                    <div class="col-lg-8">
                                        <input id="nomClient" type="text" class="form-control" name="nomClient" value="<%= ((Particulier) client).getNom()%>" required>
                                    </div>
                                </div>                                
                                <div class="form-group">
                                    <label for="prenomClient" class="col-lg-4 control-label">Prénom</label>
                                    <div class="col-lg-8">
                                        <input id="prenomClient" type="text" class="form-control" name="prenomClient" value="<%= ((Particulier) client).getPrenom()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dateNaisClient" class="col-lg-4 control-label">Date de naissance</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="dateEmbauche" value="<%= sdf.format(((Particulier) client).getDateNais())%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lieuNaisClient" class="col-lg-4 control-label">Lieu de naissance</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="lieuNaisClient" value="<%= ((Particulier) client).getLieuNaissance()%>" required>
                                    </div>
                                </div>
                                <% } else if (client instanceof Entreprise) {%>
                                <%-- Champs pour les objets Entreprise (siret, nom de l'entreprise, forme, contact, téléphoneContact) --%>
                                <div class="form-group">
                                    <label for="siret" class="col-lg-4 control-label">SIRET de l'entreprise</label>
                                    <div class="col-lg-8">
                                        <input id="siret" type="text" class="form-control" name="siret" value="<%= ((Entreprise) client).getSiret()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="nomEntreprise" class="col-lg-4 control-label">Nom de l'entreprise</label>
                                    <div class="col-lg-8">
                                        <input id="nomEntreprise" type="text" class="form-control" name="nomEntreprise" value="<%= ((Entreprise) client).getNomEntreprise()%>" required>
                                    </div>
                                </div>                                       

                                <div class="form-group">
                                    <label for="formeEntreprise" class="col-lg-4 control-label">Forme d'entreprise</label>
                                    <div class="col-lg-8">
                                        <select id="formeEntreprise" class="form-control" name="formeEntreprise" required>
                                            <option value="" selected="selected"> </option>                                                                                        
                                            <option value="SocieteAnonyme">Societe Anonyme</option>
                                            <option value="SocieteActionsSimplifiees">Societe par Actions Simplifiees</option>
                                            <option value="SocieteResponsabiliteLimitee">Societe à Responsabilité Limitée</option>
                                        </select>
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <label for="contactEntreprise" class="col-lg-4 control-label">Contact de l'entreprise</label>
                                    <div class="col-lg-8">
                                        <input id="contactEntreprise" type="text" class="form-control" name="contactEntreprise" value="<%= ((Entreprise) client).getContact()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tphContactEntreprise" class="col-lg-4 control-label">Téléphone du contact</label>
                                    <div class="col-lg-8">
                                        <input id="tphContactEntreprise" type="text" class="form-control" name="tphContactEntreprise" value="<%= ((Entreprise) client).getTphContact()%>" required>
                                    </div>
                                </div>
                                <% }%>

                                <%-- Champs pour tous les clients (téléphone, email, adresse, niveau) --%>
                                <div class="form-group">
                                    <label for="tphClient" class="col-lg-4 control-label">Téléphone Client</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="tphClient" value="<%= client.getTelephone()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="emailClient" class="col-lg-4 control-label">Email</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="emailClient" value="<%= client.getMail()%>" required>
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label for="adresseClient" class="col-lg-4 control-label">Adresse</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="adresseClient" value="<%= client.getAdresse()%>" required>
                                    </div>
                                </div>     

                                <% if (ident.getTypeUser().equalsIgnoreCase("employe")) { %>
                                <div class="form-group">
                                    <label for="niveauClient" class="col-lg-4 control-label">Niveau</label>
                                    <div class="col-lg-8">
                                        <select class="form-control" name="niveauClient" required>
                                            <option value=""> </option> 
                                            <%  
                                                for (int i = client.getCourtier().getNiveau(); i <= 5; i++) {
                                                    if (i == client.getNiveau()) {%>
                                            <option value="<%= i%>" selected="selected" > Niveau <%= " " + i%></option>
                                            <%} else {%> --%>
                                            <option value="<%= i%>" > Niveau <%= " " + i%></option>
                                            <% }
                                                }%>
                                        </select>
                                    </div> 
                                </div>  
                                <% } %>
                                        
                                <input type="hidden" name="idClient" value="<%= client.getId()%>">

                                <% if (client instanceof Particulier) { %>
                                <input type="hidden" name="action" value="modifierParticulier">
                                <% } else if (client instanceof Entreprise) { %>
                                <input type="hidden" name="action" value="modifierEntreprise">
                                <% }%>
                                <div class="row"> </div>
                                <div class="col-lg-offset-4 col-lg-8">
                                    <button type="reset" class="btn btn-primary col-lg-offset-1">Annuler</button>
                                    <button type="submit" class="btn btn-primary col-lg-offset-3">Modifier</button>
                                </div>
                            </form>
                        </div>
                    </div>


                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
