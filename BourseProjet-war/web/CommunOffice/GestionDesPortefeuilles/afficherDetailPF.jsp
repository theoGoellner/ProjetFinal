<%@page import="com.bourse.entities.PEA"%>
<%@page import="com.bourse.entities.PERP"%>
<%@page import="com.bourse.entities.Classique"%>
<%@page import="com.bourse.entities.PorteFeuille"%>
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

        <jsp:useBean id="employe" scope="session" class="com.bourse.entities.Employe"></jsp:useBean>

            <title>Consulter le détail du portefeuille</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        PorteFeuille pf = (PorteFeuille) request.getAttribute("portefeuille");
                        Employe user = (Employe) session.getAttribute("employe");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    

                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                                <fieldset>  
                                    <legend>Consulter le détail du portefeuille de 
                                        <strong><% 
                                        if (pf.getLeContrat().getLeClient() instanceof Particulier) 
                                        out.print(((Particulier)pf.getLeContrat().getLeClient()).getPrenom()
                                            +" "+((Particulier)pf.getLeContrat().getLeClient()).getNom());
                                        else out.print(((Entreprise)pf.getLeContrat().getLeClient()).getNomEntreprise());%>
                                        </strong>
                                    </legend>
                                        
                                        <div class="row"> 
                                            <div class="form-group">
                                                <label class="col-sm-6 control-label">N° de Contrat</label>
                                                <div class="col-sm-6">
                                                    <strong> <%= pf.getLeContrat().getId()%> </strong>
                                                </div>
                                            </div><br>
                                        </div>
                                        
                                        <%
                                        if (pf instanceof Classique) { %>  
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">N° de compte Classique</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getId() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                        
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Type de gestion</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getType()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Niveau de gestion</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getNiveauGestion()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Chargé de compte</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getChargeCompte()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Pourcentage Max</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getPourcentageMax()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Valeur Max</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getValeurMax() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Montant Initial</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getMontantInitial()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                                    <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Liquidités</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((Classique)pf).getLiquidite()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                        <% } else if (pf instanceof PEA) { %>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">N° de compte PEA</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PEA) pf).getId()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div> 
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Date d'ouverture</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= sdf.format(((PEA) pf).getDateOuverture()) %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Montant Initial</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PEA) pf).getMontantInitial() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Liquidités</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PEA) pf).getLiquidite() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                        <% } else { %>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">N° de compte PEP-PERP</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PERP) pf).getId()%> </strong>
                                                    </div>
                                                </div><br>
                                            </div> 
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Date d'ouverture</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= sdf.format(((PERP) pf).getDateOuverture()) %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Montant Initial</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PERP) pf).getMontantInitial() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                            <div class="row"> 
                                                <div class="form-group">
                                                    <label class="col-sm-6 control-label">Liquidités</label>
                                                    <div class="col-sm-6">
                                                        <strong> <%= ((PERP) pf).getLiquidite() %> </strong>
                                                    </div>
                                                </div><br>
                                            </div>
                                        <% } %>
                                </fieldset>
                        </div>
                    </div>

                    <%@include  file="../../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>
