<%@page import="com.bourse.entities.Entreprise"%>
<%@page import="com.bourse.entities.Employe"%>
<%@page import="com.bourse.enumeration.EnumFormEntreprise"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css">
        <link type="text/css" href="Presentation/CSS/bootstrap.min.css" rel="stylesheet">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>

        <script src="Presentation/JS/bourse.js"></script>

        <jsp:useBean id="employe" scope="session" class="com.bourse.entities.Employe"></jsp:useBean> 

            <title>Modifier Client Entreprise</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        Employe user = (Employe) session.getAttribute("employe");
                        Entreprise entr = (Entreprise) request.getAttribute("entreprise");%>
                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                            <form name="formulaireModifEntreprise" class="form-horizontal" role="form" method="get" action="controllerBackOffice">
                                <div class="modal-header">
                                    <h1 class="text-center">Modification d'un client entreprise existant</h1>
                                </div>
                                <div class="row"><p>     </p></div>                                                                 

                                <%-- Champs pour les objets Entreprise (siret, nom de l'entreprise, forme, contact, téléphoneContact) --%>
                                <div class="form-group">
                                    <label for="siret" class="col-sm-4 control-label">SIRET de l'entreprise</label>
                                    <div class="col-sm-8">
                                        <input id="siret" type="text" class="form-control" name="siret" value="<%= entr.getSiret()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="nomEntreprise" class="col-sm-4 control-label">Nom de l'entreprise</label>
                                    <div class="col-sm-8">
                                        <input id="nomEntreprise" type="text" class="form-control" name="nomEntreprise" value="<%= entr.getNomEntreprise()%>" required>
                                    </div>
                                </div>                                       

                                <div class="form-group">
                                    <label for="formeEntreprise" class="col-sm-4 control-label">Forme d'entreprise</label>
                                    <div class="col-sm-8">
                                        <select id="formeEntreprise" class="form-control" name="formeEntreprise" required>
                                            <option value="" selected="selected"> </option>                                                                                        
                                            <option value="SocieteAnonyme">Societe Anonyme</option>
                                            <option value="SocieteActionsSimplifiees">Societe par Actions Simplifiees</option>
                                            <option value="SocieteResponsabiliteLimitee">Societe à Responsabilité Limitée</option>
                                        </select>
                                    </div> 
                                </div> 
                                <div class="form-group">
                                    <label for="contactEntreprise" class="col-sm-4 control-label">Contact de l'entreprise</label>
                                    <div class="col-sm-8">
                                        <input id="contactEntreprise" type="text" class="form-control" name="contactEntreprise" value="<%= entr.getContact()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="tphContactEntreprise" class="col-sm-4 control-label">Téléphone du contact</label>
                                    <div class="col-sm-8">
                                        <input id="tphContactEntreprise" type="text" class="form-control" name="tphContactEntreprise" value="<%= entr.getTphContact()%>" required>
                                    </div>
                                </div> 

                                <%-- Champs pour tous les clients (téléphone, email, adresse, niveau) --%>
                                <div class="form-group">
                                    <label for="tphClient" class="col-sm-4 control-label">Téléphone Client</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="tphClient" value="<%= entr.getTelephone()%>" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="emailClient" class="col-sm-4 control-label">Email</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="emailClient" value="<%= entr.getMail()%>" required>
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label for="adresseClient" class="col-sm-4 control-label">Adresse</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="adresseClient" value="<%= entr.getAdresse()%>" required>
                                    </div>
                                </div>                                                                
                                <div class="form-group">
                                    <label for="niveauClient" class="col-sm-4 control-label">Niveau</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" name="niveauClient" required>
                                            <option value=""> </option> 
                                            <% for (int i = user.getNiveau(); i <= 5; i++) {
                                                    if (i == entr.getNiveau()) {%>
                                            <option value="<%= i%>" selected="selected" > Niveau <%= " " + i%></option>
                                            <%} else {%> --%>
                                            <option value="<%= i%>" > Niveau <%= " " + i%></option>
                                            <% }
                                                }%>
                                        </select>
                                    </div> 
                                </div>  

                                <input type="hidden" name="idEntreprise" value="<%= entr.getId()%>">

                                <input type="hidden" name="action" value="modifierEntreprise">
                                <div class="row"> </div>
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="reset" class="btn btn-primary col-sm-offset-1">Annuler</button>
                                    <button type="submit" class="btn btn-primary col-sm-offset-3">Modifier</button>
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
