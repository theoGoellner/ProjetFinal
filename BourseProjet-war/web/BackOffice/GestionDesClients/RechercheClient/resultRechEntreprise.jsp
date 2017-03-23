<%@page import="com.bourse.entities.Entreprise"%>
<%@page import="com.bourse.entities.Particulier"%>
<%@page import="com.bourse.enumeration.EnumFormEntreprise"%>
<%@page import="com.bourse.entities.Client"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bourse.entities.Identification"%>
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
        <jsp:useBean id="ListeDesEntreprises" scope="request" class="java.util.List"></jsp:useBean>
            <title>Résultats Recherche Entreprises</title>       
        </head>
        <body>
        <%@include  file="../../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-lg-offset-2">
            <div class="row content">
                <div class="col-lg-10 text-left"> 
                    <div align="middle"> 
                        <img src="Presentation/Images/baniere.jpg">
                    </div>
                    <hr>
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        List<Entreprise> lesEntreprises = ListeDesEntreprises;
                        String typeRechClient = (String) session.getAttribute("typeRechClient");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% } %>


                    <div class="panel panel-default">
                        <div class="panel-heading">Liste des entreprises</div>
                        <div class="panel-body">
                            <%if (ListeDesEntreprises.isEmpty())
                                    out.println("La liste des entreprises est vide !");
                                else {%>
                            <div style="overflow:scroll;height:200px;width:100%;overflow:auto">
                                <table class="table table-hover">
                                    <thead>
                                        <tr> 
                                            <td> SIRET </td> <td> Nom </td> <td> Forme </td> <td> Contact </td> <td> Telephone Contact </td> 
                                            <td> Telephone Client </td> <td> Email </td> <td> Adresse </td> <td> Niveau </td> <td> Action </td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Entreprise entr : lesEntreprises) {%>
                                        <tr> 
                                            <td > <%= entr.getSiret()%> </td>
                                            <td > <%= entr.getNomEntreprise()%> </td>
                                            <td > <%= entr.getFormeSociete()%> </td>
                                            <td > <%= entr.getContact()%> </td>
                                            <td > <%= entr.getTphContact()%> </td>
                                            <td > <%= entr.getTelephone()%> </td>
                                            <td > <%= entr.getMail()%> </td>
                                            <td > <%= entr.getAdresse()%> </td>
                                            <td > <%= entr.getNiveau()%> </td>
                                            <% if (typeRechClient.equalsIgnoreCase("gestion")) {%>
                                            <td ><a href="controllerBackOffice?action=archiverClient&idClient=<%= entr.getId()%>"> <span class="glyphicon glyphicon-trash"></span> Archiver </a>
                                            </td>
                                            <td ><a href="controllerCommun?action=formModifierClient&idClient=<%= entr.getId()%>">Modifier </a>
                                            </td>
                                            <td ><a href="controllerCommun?action=afficherPortefeuillesClient&idClient=<%= entr.getId()%>">Gestion Portefeuilles </a>
                                            </td>
                                            <% } else if (typeRechClient.equalsIgnoreCase("versement")) {%>
                                            <td ><a href="controllerBackOffice?action=formGestionVersements&idClient=<%= entr.getId()%>">Versements </a>
                                            </td>
                                            <% } else if (typeRechClient.equalsIgnoreCase("PF")) {%>
                                            <td ><a href="controllerBackOffice?action=formAjoutContrat&idClient=<%= entr.getId()%>">Nouveau Contrat </a>
                                            </td>
                                            <% } %>
                                        </tr> 
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                            <%}%>  
                        </div> 
                    </div>

                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireRechercheClient" class="form-horizontal" method="post" action="controllerBackOffice">
                                <fieldset>
                                    <legend>Effectuer une autre recherche</legend>
                                    <div class="row"><p>     </p></div>  

                                    <div class="form-group">
                                        <label for="typeClient" class="col-lg-3 control-label">Type de client</label>
                                        <div class="col-lg-9">
                                            <select id="typeClient" class="form-control" name="typeClient" onChange="majAffichageTypeClientRecherche()" required>
                                                <option value="" selected="selected"> </option>                                                                                        
                                                <option value="Particulier">Particulier</option>
                                                <option value="Entreprise">Entreprise</option>
                                            </select>
                                        </div> 
                                    </div>

                                    <%-- Champs pour les objets Particulier (nom, prénom, date de naissance, lieu de naissance) --%>
                                    <div id="champsParticulier" style="display:none">                                       
                                        <div class="form-group">
                                            <label for="nomClient" class="col-lg-3 control-label">Nom</label>
                                            <div class="col-lg-9">
                                                <input id="nomClient" type="text" class="form-control" name="nomClient" placeholder="Saisir le nom du client" required>
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label for="prenomClient" class="col-lg-3 control-label">Prénom</label>
                                            <div class="col-lg-9">
                                                <input id="prenomClient" type="text" class="form-control" name="prenomClient" placeholder="Saisir le prénom du client" required>
                                            </div>
                                        </div>
                                    </div>

                                    <%-- Champs pour les objets Entreprise (siret, nom de l'entreprise, forme, contact, téléphoneContact) --%>
                                    <div id="champsEntreprise" style="display:none">
                                        <div class="form-group">
                                            <label for="siret" class="col-lg-3 control-label">SIRET de l'entreprise</label>
                                            <div class="col-lg-9">
                                                <input id="siret" type="text" class="form-control" name="siret" placeholder="Saisir le SIRET de l'entreprise" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nomEntreprise" class="col-lg-3 control-label">Nom de l'entreprise</label>
                                            <div class="col-lg-9">
                                                <input id="nomEntreprise" type="text" class="form-control" name="nomEntreprise" placeholder="Saisir le nom de l'entreprise" required>
                                            </div>
                                        </div>
                                    </div>

                                    <input type="hidden" name="action" value="rechClient">
                                    <div class="row"> </div>
                                    <div class="col-lg-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-lg-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-lg-offset-3">Nouvelle Recherche</button>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>                                                                                                                                



                    <%@include  file="../../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
