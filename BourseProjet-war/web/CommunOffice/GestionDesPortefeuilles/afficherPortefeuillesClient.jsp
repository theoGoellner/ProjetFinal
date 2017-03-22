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
            <title>Consulter la liste des portefeuilles</title>       
        </head>
        <body>
        <%  Identification ident = (Identification) session.getAttribute("identification");
            Client client = null;
            if (ident.getTypeUser().equalsIgnoreCase("employe")) {
                client = (Client) request.getAttribute("client");
              %> 
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
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        List<Contrat> listeContrats = client.getLesContrats();
                        if (attribut.length() > 8) { %>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% } %>                    
                    <legend>Consulter la liste des portefeuilles</legend>                                      
                    <div class="panel panel-default">
                        <br>
                        <%if (listeContrats.isEmpty())
                                out.println("La liste des portefeuilles est vide !");
                            else {%>
                        <table class="table table-hover">
                            <thead>
                                <tr> 
                                    <td> N°Contrat </td > <td> Type de portefeuille </td> <td> Type de contrat </td> <td> Date signature </td>  
                                    <td> Date d'ouverture </td> <td> Montant initial </td> <td> Liquidité </td> <td> Action </td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Contrat contr : listeContrats) {%>
                                <tr> 
                                    <td > <%= contr.getId()%> </td>
                                    <td > 
                                        <% if (contr.getPorteFeuille() instanceof PEA) {
                                                out.print("PEA");
                                            } else if (contr.getPorteFeuille() instanceof Classique) {
                                                out.print("Classique");
                                            } else {
                                                out.print("PEP-PERP");
                                            }
                                        %> 
                                    </td>
                                    <td > <%= contr.getTypeContrat()%> </td>
                                    <td > <%= sdf.format(contr.getDateDebut())%> </td>
                                    <td > 
                                        <% if (contr.getPorteFeuille() instanceof PEA) {
                                                out.print(sdf.format(((PEA) contr.getPorteFeuille()).getDateOuverture()));
                                            } else if (contr.getPorteFeuille() instanceof PERP) {
                                                out.print(sdf.format(((PERP) contr.getPorteFeuille()).getDateOuverture()));
                                            } else {
                                                out.print("---------");
                                            }%>                                          
                                    </td>
                                    <td > <%= contr.getPorteFeuille().getMontantInitial()%> </td> 
                                    <td > <%= contr.getPorteFeuille().getLiquidite()%> </td>
                                    <td ><a href="controllerCommun?action=afficherDetailPF&idPF=<%= contr.getPorteFeuille().getId()%>"> Détail </a>
                                    </td>
                                </tr>                                 
                                <% } %>
                            </tbody>
                        </table>
                        <%}%>  
                    </div>

                    <% if (ident.getTypeUser().equalsIgnoreCase("employe")) {%>
                    <div class="row" align="middle"> 
                        <a href="controllerBackOffice?action=formAjoutContrat&idClient=<%= client.getId()%>" class="btn btn-primary">Nouveau portefeuille</a>
                    </div>
                    <% }%>
                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
