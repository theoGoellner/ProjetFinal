<%@page import="java.text.DecimalFormat"%>
<%@page import="com.bourse.entities.Courtage"%>
<%@page import="com.bourse.entities.Titre"%>
<%@page import="com.bourse.entities.PEA"%>
<%@page import="com.bourse.entities.PERP"%>
<%@page import="com.bourse.entities.Classique"%>
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
        <title>Creer une proposition d'operation</title>       
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
                        Courtage cour = (Courtage) request.getAttribute("cour");
                        Titre titre = cour.getLeTitre();
                        DecimalFormat df = new DecimalFormat();
                        df.setMaximumFractionDigits(2);
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    

                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <fieldset>  
                                <legend>Détail du titre</legend>

                                <div class="row"> 
                                    <div class="form-group">
                                        <label class="col-lg-6 control-label">Nom du titre</label>
                                        <div class="col-lg-6">
                                            <strong> <%= titre.getNom()%> </strong>
                                        </div>
                                    </div><br>
                                </div>
                                <div class="row"> 
                                    <div class="form-group">
                                        <label class="col-lg-6 control-label">Montant Nominal</label>
                                        <div class="col-lg-6">
                                            <strong> <%= titre.getMontantNominal()%> </strong>
                                        </div>
                                    </div><br>
                                </div>
                                <div class="row"> 
                                    <div class="form-group">
                                        <label class="col-lg-6 control-label">Cour actuel</label>
                                        <div class="col-lg-6">
                                            <strong> <%= cour.getCours()%> </strong>
                                        </div>
                                    </div><br>
                                </div>
                                <div class="row"> 
                                    <div class="form-group">
                                        <label class="col-lg-6 control-label">Variation</label>
                                        <div class="col-lg-6">
                                            <strong> <%= df.format((cour.getCours() - titre.getMontantNominal()) / titre.getMontantNominal()) + " %"%> </strong>
                                        </div>
                                    </div><br>
                                </div>

                            </fieldset>
                        </div>
                    </div>
                    <iframe width="800" height="600" src="https://app.powerbi.com/view?r=eyJrIjoiMTYzNzBiMTUtNWMzNC00ZTZjLTg2OTctZDIzYjQ4OGVlYWE1IiwidCI6IjAwMzJhZjMyLTBhZTAtNDMwMC1iZjY3LTgwOWM2MjA4NGU2NiIsImMiOjh9" frameborder="0" allowFullScreen="true"></iframe>
                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
