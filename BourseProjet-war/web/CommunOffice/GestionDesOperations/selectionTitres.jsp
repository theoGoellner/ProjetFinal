<%@page import="com.bourse.entities.Courtage"%>
<%@page import="com.bourse.entities.PorteFeuille"%>
<%@page import="com.bourse.entities.Entreprise"%>
<%@page import="com.bourse.entities.Particulier"%>
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
        <jsp:useBean id="listeCours" scope="request" class="java.util.List"></jsp:useBean>
        <jsp:useBean id="identification" scope="session" class="com.bourse.entities.Identification"></jsp:useBean>
        <title>Consulter la liste des titres</title>       
        </head>
        <body>
        <%  Identification ident = (Identification) session.getAttribute("identification");
            if (ident.getTypeUser().equalsIgnoreCase("employe")) { %> 
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
            <% } else { %> 
        <%@include  file="../../jsp_commun/menuFrontOffice.jsp" %>
        <% } %>

        <div class="container-fluid text-center col-lg-offset-2">
            <div class="row content">
                <div class="col-lg-10 text-left"> 
                    <div align="middle"> 
                        <img src="Presentation/Images/baniere.jpg">
                    </div>
                    <hr>
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        List<Courtage> listeCour = listeCours;
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    
                    <legend>Consulter la liste des titres</legend>                                      
                    
                        <br>
                        <%if (listeCour.isEmpty())
                                out.println("La liste des courtage est vide ! C'est pas normal ...");
                            else {%>
                        <div class="panel panel-default" style="overflow:scroll;height:400px;width:100%;overflow:auto">
                        <table class="table table-hover table-fixed" height="100px">
                            <thead>
                                <tr> 
                                    <td> Nom Titre </td> <td> Marché </td> <td> Date dernier courtage </td> <td> Cours actuel (%) </td> <td> Action </td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Courtage cour : listeCour) {%>
                                <tr> 
                                    <td > <%= cour.getLeTitre().getNom()%> </td>
                                    <td > <%= cour.getLeTitre().getLeMarche().getNom()%> </td>
                                    <td > <%= sdf.format(cour.getDateCourtage().getDateCourtage())%> </td>
                                    <td > <%= cour.getCours()%> </td>
                                    <td >
                                        <% if (ident.getTypeUser().equalsIgnoreCase("employe")) {%>
                                        <a href="controllerBackOffice?action=achatCour&idCour=<%= cour.getId()%>"> Acheter </a>
                                        <% out.println(" - ");%>
                                        <a href="controllerBackOffice?action=propositionCour&idCour=<%= cour.getId()%>"> Proposer </a>
                                        <% } else {%>
                                        <a href="controllerFrontOffice?action=propositionCour&idCour=<%= cour.getId()%>"> Proposer </a>
                                        <% } %>                                        
                                    </td>
                                </tr>                                 
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                        <%}%>  
                    
                    <div class="row" align="middle"> 
                        <a href="controllerBackOffice?action=acheterTitres" class="btn btn-primary">Acheter</a>
                    </div>
                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
