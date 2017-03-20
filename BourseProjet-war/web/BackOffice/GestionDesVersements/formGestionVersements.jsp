<%@page import="com.bourse.entities.Versement"%>
<%@page import="com.bourse.entities.PERP"%>
<%@page import="com.bourse.entities.Classique"%>
<%@page import="com.bourse.entities.PEA"%>
<%@page import="com.bourse.entities.Contrat"%>
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
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>

        <script src="Presentation/JS/bourse.js"></script>

        <jsp:useBean id="employe" scope="session" class="com.bourse.entities.Employe"></jsp:useBean>
        <jsp:useBean id="clientSelectionne" scope="session" class="com.bourse.entities.Client"></jsp:useBean>



            <title>Gestion des versements</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        Employe user = (Employe) session.getAttribute("employe");
                        Client cli = (Client) session.getAttribute("clientSelectionne");
                        List<Versement> listeVers = cli.getLesVersements();

                        //<jsp:useBean id="listePF" scope="session" class="com.bourse.entities.PorteFeuille"></jsp:useBean>
                        //List<PorteFeuille> listePFs = (List<PorteFeuille>)request.getAttribute("listePF");                      
                        //<jsp:useBean id="listePF" scope="request" class="java.util.List"></jsp:useBean>
                        //List<PorteFeuille> listePFs = listePF;
                        //System.out.println("coco6");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    

                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                            <form name="gestionVersements" class="form-horizontal" method="get" action="controllerBackOffice">
                                <fieldset>

                                    <legend>Effectuer un versement</legend>                                      

                                    <div class="form-group"">
                                        <label for="respSection" class="col-lg-3 control-label">Sélectionner un portefeuille</label>
                                        <div class="col-lg-9">
                                            <select id="selectPF" class="form-control" name="idPF">
                                                <option value="" selected="selected"> </option> 
                                                <%
                                                    List<Contrat> listeContr = cli.getLesContrats();
                                                    for (Contrat cont : listeContr) {
                                                        if (cont.getPorteFeuille() instanceof PEA) {%>
                                                <option  value="<%=cont.getPorteFeuille().getId()%>"> 
                                                    <%= "Type PEA n°" + cont.getPorteFeuille().getId()
                                                            + " (solde actuel : " + cont.getPorteFeuille().getLiquidite() + " €)"%></option>
                                                    <% } else if (cont.getPorteFeuille() instanceof PERP) {%>
                                                <option  value="<%=cont.getPorteFeuille().getId()%>"> 
                                                    <%= "Type PERP n°" + cont.getPorteFeuille().getId()
                                                            + " (solde actuel : " + cont.getPorteFeuille().getLiquidite() + " €)"%></option>
                                                    <% } else if (cont.getPorteFeuille() instanceof Classique) {%>
                                                <option  value="<%=cont.getPorteFeuille().getId()%>"> 
                                                    <%= "Type Classique n°" + cont.getPorteFeuille().getId()
                                                            + " (solde actuel : " + cont.getPorteFeuille().getLiquidite() + " €)"%></option>
                                                    <% }
                                                        }%>   
                                            </select>
                                        </div> 
                                    </div> 

                                    <div class="form-group">
                                        <label for="montantVersement" class="col-lg-3 control-label">Montant du versement</label>
                                        <div class="col-lg-9">
                                            <input id="montantVersement" type="text" class="form-control" name="montantVersement" placeholder="Saisir le montant du versement" required>
                                        </div>
                                    </div>

                                    <input type="hidden" name="action" value="validerVersement">
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
                            <a href="#historiqueVersements" class="btn btn-primary" data-toggle="collapse">Afficher les derniers versements</a>
                        </div>
                    </div>
                    <div id="historiqueVersements" class="collapse">
                        <br>
                        <%if (listeVers.isEmpty())
                                out.println("Aucun versement effectué !");
                            else {%>
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
                                    <% } %>
                                    <td > <%= vers.getLePortefeuille().getId()%> </td>
                                    <td > <%= vers.getMontant() %> </td>
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
