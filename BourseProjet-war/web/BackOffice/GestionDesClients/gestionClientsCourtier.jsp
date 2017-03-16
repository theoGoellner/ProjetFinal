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

            <title>Gestion Client</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
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

                    <legend>Gestion de mes clients</legend>
                    
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
                                    <td ><a href="controllerBackOffice?action=archiverClientGestion&idClient=<%= part.getId()%>"> Archiver </a>
                                    </td>
                                    <td ><a href="controllerBackOffice?action=formModifierClient&idClient=<%= part.getId()%>"> Modifier </a>
                                    </td>
                                    <td ><a href="controllerBackOffice?action=gestionContratsClient&idClient=<%= part.getId()%>"> Contrats </a>
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
                                    <td ><a href="controllerBackOffice?action=gestionContratsClient&idClient=<%= entr.getId() %>"> Contrats </a>
                                    </td>
                                </tr> 
                                <%}%>
                            </tbody>
                        </table>
                        <%}%>  
                    </div>

                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
