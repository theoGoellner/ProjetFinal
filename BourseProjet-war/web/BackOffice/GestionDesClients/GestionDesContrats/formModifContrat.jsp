<%@page import="com.bourse.entities.Entreprise"%>
<%@page import="com.bourse.entities.Particulier"%>
<%@page import="com.bourse.entities.Employe"%>
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
        <link type="text/css" href="Presentation/CSS/bootstrap.min.css" rel="stylesheet">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>

        <script src="Presentation/JS/bourse.js"></script>

        <jsp:useBean id="ListeDesParticuliers" scope="request" class="java.util.List"></jsp:useBean>   
        <jsp:useBean id="ListeDesEntreprises" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="employe" scope="session" class="com.bourse.entities.Employe"></jsp:useBean> 

            <title>Nouveau Client</title>       
        </head>
        <body>
        <%@include  file="../../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        List<Particulier> lesParticuliers = ListeDesParticuliers;
                        List<Entreprise> lesEntreprises = ListeDesEntreprises;
                        Employe user = (Employe) session.getAttribute("employe");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% } %>
                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                            <form name="formulaireAjoutClient" class="form-horizontal" method="get" action="controllerBackOffice">
                                <fieldset>
                                    <legend>Création d'un nouveau client</legend>
                                    <div class="row"><p>     </p></div>  

                                    <div class="form-group">
                                        <label for="typeClient" class="col-lg-3 control-label">Type de client</label>
                                        <div class="col-lg-9">
                                            <select id="typeClient" class="form-control" name="typeClient" onChange="majAffichageTypeClient()" required>
                                                <option value="" selected="selected"> </option>                                                                                        
                                                <option value="Particulier">Particulier</option>
                                                <option value="Entreprise">Entreprise</option>
                                            </select>
                                        </div> 
                                    </div>

                                    <div id="champsParticulier" style="display:none">
                                        <%-- Champs pour les objets Particulier (nom, prénom, date de naissance, lieu de naissance) --%>
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

                                        <div class="form-group">
                                            <label for="dateNaisClient" class="col-lg-3 control-label">Date de naissance</label>
                                            <div class="col-lg-9">
                                                <input id="dateNaisClient" type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateNaisClient" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="lieuNaisClient" class="col-lg-3 control-label">Lieu de naissance</label>
                                            <div class="col-lg-9">
                                                <input id="lieuNaisClient" type="text" class="form-control" name="lieuNaisClient" placeholder="Saisir le lieu de naissance du client" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="champsEntreprise" style="display:none">
                                        <%-- Champs pour les objets Entreprise (siret, nom de l'entreprise, forme, contact, téléphoneContact) --%>
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
                                        <div class="form-group">
                                            <label for="formeEntreprise" class="col-lg-3 control-label">Forme d'entreprise</label>
                                            <div class="col-lg-9">
                                                <select id="formeEntreprise" class="form-control" name="formeEntreprise" required>
                                                    <option value="" selected="selected"> </option>                                                                                        
                                                    <option value="SocieteAnonyme">Societe Anonyme</option>
                                                    <option value="SocieteActionsSimplifiees">Societe par Actions Simplifiees</option>
                                                    <option value="SocieteResponsabiliteLimitee">Societe à Responsabilité Limitée</option>
                                                </select>
                                            </div> 
                                        </div> 
                                        <div class="form-group">
                                            <label for="contactEntreprise" class="col-lg-3 control-label">Contact de l'entreprise</label>
                                            <div class="col-lg-9">
                                                <input id="contactEntreprise" type="text" class="form-control" name="contactEntreprise" placeholder="Saisir le contact de l'entreprise" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="tphContactEntreprise" class="col-lg-3 control-label">Téléphone du contact</label>
                                            <div class="col-lg-9">
                                                <input id="tphContactEntreprise" type="text" class="form-control" name="tphContactEntreprise" placeholder="Saisir le téléphone du contact de l'entreprise" required>
                                            </div>
                                        </div> 
                                    </div>

                                    <%-- Champs pour tous les clients (téléphone, email, adresse, niveau) --%>
                                    <div class="form-group">
                                        <label for="tphClient" class="col-lg-3 control-label">Téléphone Client</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="tphClient" placeholder="Saisir le téléphone du client" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="emailClient" class="col-lg-3 control-label">Email</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="emailClient" placeholder="Saisir l'email du client" required>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="adresseClient" class="col-lg-3 control-label">Adresse</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="adresseClient" placeholder="Saisir l'adresse du client" required>
                                        </div>
                                    </div>                                                                
                                    <div class="form-group">
                                        <label for="niveauClient" class="col-lg-3 control-label">Niveau</label>
                                        <div class="col-lg-9">
                                            <select class="form-control" name="niveauClient" required>
                                                <option value="" selected="selected"> </option> 
                                                <% for (int i = user.getNiveau(); i <= 5; i++) {%>
                                                <option  value="<%= i%>" > Niveau <%= " " + i%></option>
                                                <%}%>
                                            </select>
                                        </div> 
                                    </div>     

                                    <div class="form-group">
                                        <label for="login" class="col-lg-3 control-label">Identifiant</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="login" placeholder="Saisir l'identifiant du client" required>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="pwd" class="col-lg-3 control-label">Mot de passe</label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="pwd" placeholder="Saisir le mot de passe du client" required>
                                        </div>
                                    </div>                                                              

                                    <input type="hidden" name="action" value="ajoutClient">
                                    <div class="row"> </div>
                                    <div class="col-sm-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-sm-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-sm-offset-3">Valider</button>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>

                    <div class="row">
                        <div class="well text-center"> 
                            <a href="#infoParticuliers" class="btn btn-primary" data-toggle="collapse">Afficher la liste des particuliers</a>
                        </div>
                    </div>
                    <div id="infoParticuliers" class="collapse">
                        <br>
                        <%if (ListeDesParticuliers.isEmpty())
                                out.println("La liste des particuliers est vide !");
                            else {%>
                        <table class="table table-hover">
                            <thead>
                                <tr> 
                                    <td> Nom </td> <td> Prénom </td> <td> Date de naissance </td> <td> Lieu de naissance </td> 
                                    <td> Téléphone </td> <td> Email </td> <td> Adresse </td> <td> Niveau </td> <td> Action </td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Particulier part : lesParticuliers) {%>
                                <tr> 
                                    <td > <%= part.getNom()%> </td>
                                    <td > <%= part.getPrenom()%> </td>
                                    <td > <%= sdf.format(part.getDateNais())%> </td>
                                    <td > <%= part.getLieuNaissance()%> </td>
                                    <td > <%= part.getTelephone()%> </td>
                                    <td > <%= part.getMail()%> </td>
                                    <td > <%= part.getAdresse()%> </td>
                                    <td > <%= part.getNiveau()%> </td>
                                    <td ><a href="controllerBackOffice?action=archiverClient&idClient=<%= part.getId()%>"> Archiver </a>
                                    </td>
                                    <td ><a href="controllerBackOffice?action=formModifierClient&idClient=<%= part.getId()%>"> Modifier </a>
                                    </td>
                                </tr> 
                                <%}%>
                            </tbody>
                        </table>
                        <%}%>  
                    </div>

                    <div class="row">
                        <div class="well text-center"> 
                            <a href="#infoEntreprises" class="btn btn-primary" data-toggle="collapse">Afficher la liste des entreprises</a>
                        </div>
                    </div>
                    <div id="infoEntreprises" class="collapse">
                        <br>
                        <%if (ListeDesEntreprises.isEmpty())
                                out.println("La liste des entreprises est vide !");
                            else {%>
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
                                    <td ><a href="controllerBackOffice?action=archiverClient&idClient=<%= entr.getId()%>"> <span class="glyphicon glyphicon-trash"></span> Archiver </a>
                                    </td>
                                    <td ><a href="controllerBackOffice?action=formModifierClient&idClient=<%= entr.getId()%>"> Modifier </a>
                                    </td>
                                </tr> 
                                <%}%>
                            </tbody>
                        </table>
                        <%}%>  
                    </div>

                    <%@include  file="../../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
