<%@page import="com.bourse.entities.Particulier"%>
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

        <title>Modifier Client Particulier</title>       
    </head>
    <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-lg-offset-2">
            <div class="row content">
                <div class="col-lg-10 text-left"> 
                    <div align="middle"> 
                        <img src="Presentation/Images/baniere.jpg">
                    </div>
                    <hr>
                    <%  Particulier part = (Particulier) request.getAttribute("particulier");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    %>
                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireModifParticulier" class="form-horizontal" role="form" method="get" action="controllerBackOffice">
                                <div class="modal-header">
                                    <h1 class="text-center">Modification d'un client particulier existant</h1>
                                </div>
                                <div class="row"><p>     </p></div>  
                                <%-- Champs pour les objets Particulier (nom, prénom, date de naissance, lieu de naissance) --%>
                                <div class="form-group">
                                    <label for="nomClient" class="col-lg-4 control-label">Nom</label>
                                    <div class="col-lg-8">
                                        <input id="nomClient" type="text" class="form-control" name="nomClient" value="<%= part.getNom() %>" required>
                                    </div>
                                </div>                                
                                <div class="form-group">
                                    <label for="prenomClient" class="col-lg-4 control-label">Prénom</label>
                                    <div class="col-lg-8">
                                        <input id="prenomClient" type="text" class="form-control" name="prenomClient" value="<%= part.getPrenom() %>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="dateNaisClient" class="col-lg-4 control-label">Date de naissance</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="dateEmbauche" value="<%= sdf.format(part.getDateNais()) %>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="lieuNaisClient" class="col-lg-4 control-label">Lieu de naissance</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="lieuNaisClient" value="<%= part.getLieuNaissance() %>" required>
                                    </div>
                                </div>
                                <%-- Champs pour tous les clients (téléphone, email, adresse, niveau) --%>
                                <div class="form-group">
                                    <label for="tphClient" class="col-lg-4 control-label">Téléphone Client</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="tphClient" value="<%= part.getTelephone() %>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="emailClient" class="col-lg-4 control-label">Email</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="emailClient" value="<%= part.getMail() %>" required>
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label for="adresseClient" class="col-lg-4 control-label">Adresse</label>
                                    <div class="col-lg-8">
                                        <input type="text" class="form-control" name="adresseClient" value="<%= part.getAdresse() %>" required>
                                    </div>
                                </div>     
                                
                                <div class="form-group">
                                    <label for="niveauClient" class="col-lg-4 control-label">Niveau</label>
                                    <div class="col-lg-8">
                                        <select class="form-control" name="niveauClient" required>
                                            <option value=""> </option> 
                                            <% for (int i = user.getNiveau(); i <= 5; i++) { 
                                            if (i==part.getNiveau()) { %>
                                                <option value="<%= i %>" selected="selected" > Niveau <%= " "+i %></option>
                                            <%} else {%> --%>
                                                <option value="<%= i %>" > Niveau <%= " "+i %></option>
                                            <% }} %>
                                        </select>
                                    </div> 
                                </div>  
                                                                                
                                <input type="hidden" name="idParticulier" value="<%= part.getId() %>">

                                <input type="hidden" name="action" value="modifierParticulier">
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
