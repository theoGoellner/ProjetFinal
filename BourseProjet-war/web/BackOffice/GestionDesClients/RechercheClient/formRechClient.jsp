<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>
        <script src="Presentation/JS/bourse.js"></script>


            <title>Recherche Client</title>       
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
                        if (attribut.length() > 8) {%>                       
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>
                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireRechercheClient" class="form-horizontal" method="post" action="controllerBackOffice">
                                <fieldset>
                                    <legend>Rechercher un client</legend>
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
                                                <input id="nomClient" type="text" class="form-control" name="nomClient" placeholder="Saisir le nom du client">
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label for="prenomClient" class="col-lg-3 control-label">Prénom</label>
                                            <div class="col-lg-9">
                                                <input id="prenomClient" type="text" class="form-control" name="prenomClient" placeholder="Saisir le prénom du client">
                                            </div>
                                        </div>
                                    </div>

                                    <%-- Champs pour les objets Entreprise (siret, nom de l'entreprise, forme, contact, téléphoneContact) --%>
                                    <div id="champsEntreprise" style="display:none">
                                        <div class="form-group">
                                            <label for="siret" class="col-lg-3 control-label">SIRET de l'entreprise</label>
                                            <div class="col-lg-9">
                                                <input id="siret" type="text" class="form-control" name="siret" placeholder="Saisir le SIRET de l'entreprise">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="nomEntreprise" class="col-lg-3 control-label">Nom de l'entreprise</label>
                                            <div class="col-lg-9">
                                                <input id="nomEntreprise" type="text" class="form-control" name="nomEntreprise" placeholder="Saisir le nom de l'entreprise">
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <input type="hidden" name="action" value="rechClient">
                                    <div class="row"> </div>
                                    <div class="col-lg-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-lg-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-lg-offset-3">Valider</button>
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
