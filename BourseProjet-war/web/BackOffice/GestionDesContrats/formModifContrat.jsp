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
        <title>Modification du contrat</title>       
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
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        Client client = (Client) request.getAttribute("client");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% } %>
                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireModifContrat" class="form-horizontal" method="get" action="controllerBackOffice">
                                <fieldset>
                                    <legend>Modification du contrat</legend>
                                    <div class="row"><p>     </p></div>  

                                                                                                  

                                    <input type="hidden" name="action" value="modifierContrat">
                                    <div class="row"> </div>
                                    <div class="col-lg-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-lg-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-lg-offset-3">Valider</button>
                                    </div>
                                </fieldset>
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
