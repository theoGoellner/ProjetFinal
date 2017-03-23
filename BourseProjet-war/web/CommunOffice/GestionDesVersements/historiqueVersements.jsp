<%@page import="com.bourse.entities.Versement"%>
<%@page import="com.bourse.entities.PERP"%>
<%@page import="com.bourse.entities.Classique"%>
<%@page import="com.bourse.entities.PEA"%>
<%@page import="com.bourse.entities.Contrat"%>
<%@page import="com.bourse.entities.PorteFeuille"%>
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
        <jsp:useBean id="identification" scope="session" class="com.bourse.entities.Identification"></jsp:useBean>
            <title>Gestion des versements</title>       
        </head>
        <body>
        <%  Identification ident = (Identification) session.getAttribute("identification");
            Client client = null;
            if (ident.getTypeUser().equalsIgnoreCase("employe")) { %> 
                <jsp:useBean id="clientSelectionne" scope="session" class="com.bourse.entities.Client"></jsp:useBean>
                <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
                <% client = (Client) session.getAttribute("clientSelectionne");
            } else {
                client = (Client) session.getAttribute("client");
                %>
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
                        List<Versement> listeVers = client.getLesVersements();
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    


                    <div class="panel panel-default">
                        <div class="panel-heading">Historique des versements</div>
                        <div class="panel-body">
                            <%if (listeVers.isEmpty())
                                    out.println("Aucun versement effectué !");
                                else {%>
                            <div style="overflow:scroll;height:200px;width:100%;overflow:auto">

                                <table class="table table-hover">
                                    <thead>
                                        <tr> 
                                            <td> Date </td> <td> Type de Portefeuille </td> <td> N°PorteFeuille </td> <td> Montant du versement </td> 
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% for (Versement vers : listeVers) {%>
                                        <tr> 
                                            <td > <%= sdf.format(vers.getDateVersement())%> </td>
                                            <% if (vers.getLePortefeuille() instanceof PEA) { %>
                                            <td > PEA </td>
                                            <% } else if (vers.getLePortefeuille() instanceof PERP) { %>
                                            <td > PERP </td>
                                            <% } else if (vers.getLePortefeuille() instanceof Classique) { %>
                                            <td > Classique </td> 
                                            <% }%>
                                            <td > <%= vers.getLePortefeuille().getId()%> </td>
                                            <td > <%= vers.getMontant()%> </td>
                                        </tr> 
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                            <%}%>  
                        </div>
                    </div>

                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
