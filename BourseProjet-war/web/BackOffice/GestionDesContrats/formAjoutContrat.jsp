<%@page import="com.bourse.entities.PEA"%>
<%@page import="com.bourse.entities.Contrat"%>
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

            <title>Création d'un nouveau contrat</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy");
                        Employe user = (Employe) session.getAttribute("employe");
                        Client client = (Client) request.getAttribute("client");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>                    

                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                            <form name="formulaireAjoutContrat" class="form-horizontal" method="get" action="controllerBackOffice">
                                <fieldset>
                                    <legend>Création d'un nouveau contrat</legend>
                                    <div class="row"><p>     </p></div>

                                    <div class="form-group">
                                        <label for="typePorteFeuille" class="col-lg-3 control-label">Type de portefeuille</label>
                                        <div class="col-lg-9">
                                            <select id="typePorteFeuille" class="form-control" name="typePorteFeuille" onChange="majAffichageTypePorteFeuille()" required>
                                                <option value="" selected="selected"> </option>                                                                                        
                                                <option value="Classique">Classique</option>                                                
                                                
                                                
                                                <% if (client instanceof Particulier) {
                                                    %> <option value="PEP-PERP">PEP-PERP</option> <%
                                                    boolean b = false;
                                                    for (Contrat c : client.getLesContrats()){
                                                        if (c.getPorteFeuille() instanceof PEA) b=true;
                                                    }  
                                                    if (b==false) { %>
                                                        <option value="PEA">PEA</option>
                                                    <% }
                                                                                    
                                                    } %>
                                            </select>
                                        </div> 
                                    </div>

                                    <div id="champsContrat">
                                        <%-- Champs pour le contrat (dateDebut, RIB, typeContrat) --%>
                                        <div class="form-group">
                                            <label for="dateDebutContrat" class="col-lg-3 control-label">Date de début du contrat</label>
                                            <div class="col-lg-9">
                                                <input id="dateDebutContrat" type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateDebutContrat" required>
                                            </div>
                                        </div>  

                                        <div class="form-group">
                                            <label for="ribContrat" class="col-lg-3 control-label">RIB</label>
                                            <div class="col-lg-9">
                                                <input id="ribContrat" type="text" class="form-control" name="ribContrat" placeholder="Saisir le RIB associé au contrat" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="typeContrat" class="col-lg-3 control-label">Type de contrat</label>
                                            <div class="col-lg-9">
                                                <select id="typeContrat" class="form-control" name="typeContrat" required>
                                                    <option value="" selected="selected"> </option>                                                                                        
                                                    <option value="Classique">Classique </option>
                                                    <option value="Autre">Autre </option>
                                                </select>
                                            </div>
                                        </div>                        
                                    </div>

                                    <div class="form-group">
                                        <label for="montantInitialPF" class="col-lg-3 control-label">Montant initial</label>
                                        <div class="col-lg-9">
                                            <input id="montantInitialPF" type="text" class="form-control" name="montantInitialPF" placeholder="Saisir le montant initial du portefeuille" required>
                                        </div>
                                    </div>

                                    <div id="champsPFClassique" style="display:none">
                                        <%-- Champs pour les portefeuille Classique (typeClassique, niveauGestion, nomChargeCompte, valeurMax, pourcMax) --%>
                                        <div class="form-group">
                                            <label for="typePFClassique" class="col-lg-3 control-label">Type de portefeuille classique</label>
                                            <div class="col-lg-9">
                                                <select id="typePFClassique" class="form-control" name="typePFClassique" required>
                                                    <option value="" selected="selected"> </option>                                                                                        
                                                    <option value="Libre">Libre</option>
                                                    <option value="Guidee">Guidee</option>
                                                    <option value="Passive">Passive</option>
                                                </select>
                                            </div>
                                        </div>  

                                        <div class="form-group">
                                            <label for="niveauGestionClassique" class="col-lg-3 control-label">Niveau de gestion</label>
                                            <div class="col-lg-9">
                                                <select id="niveauGestionClassique" class="form-control" name="niveauGestionClassique" required>
                                                    <option value="" selected="selected"> </option>                                                                                        
                                                    <option value="Dynamique">Dynamique</option>
                                                    <option value="Equilibre">Equilibré</option>
                                                    <option value="Securise">Securisé</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="nomChargeCompte" class="col-lg-3 control-label">Nom du chargé du compte</label>
                                            <div class="col-lg-9">
                                                <input id="nomChargeCompte" type="text" class="form-control" name="nomChargeCompte" placeholder="Saisir le nom du chargé du compte" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="valeurMaxClassique" class="col-lg-3 control-label">Valeur maximale</label>
                                            <div class="col-lg-9">
                                                <input id="valeurMaxClassique" type="text" class="form-control" name="valeurMaxClassique" placeholder="Saisir la valeur maximale" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="pourcMaxClassique" class="col-lg-3 control-label">Pourcentage maximal</label>
                                            <div class="col-lg-9">
                                                <input id="pourcMaxClassique" type="text" class="form-control" name="pourcMaxClassique" placeholder="Saisir le pourcentage maximal" required>
                                            </div>
                                        </div>
                                    </div>

                                    <div id="champsPFPEA" style="display:none">
                                        <%-- Champs pour les portefeuille PEA (dateOuverture) --%>
                                        <div class="form-group">
                                            <label for="dateOuverturePEA" class="col-lg-3 control-label">Date d'ouverture du PEA</label>
                                            <div class="col-lg-9">
                                                <input id="dateOuverturePEA" type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateOuverturePEA" required>
                                            </div>
                                        </div>
                                    </div>


                                    <div id="champsPFPEP" style="display:none">
                                        <%-- Champs pour les portefeuille PEP-PERP (dateOuverture, dateFermeture) --%>
                                        <div class="form-group">
                                            <label for="dateOuverturePEP" class="col-lg-3 control-label">Date d'ouverture du PEP-PERP</label>
                                            <div class="col-lg-9">
                                                <input id="dateOuverturePEP" type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateOuverturePEP" required>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="dateFermeturePEP" class="col-lg-3 control-label">Date de fermeture du PEP-PERP</label>
                                            <div class="col-lg-9">
                                                <input id="dateFermeturePEP" type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateFermeturePEP" required>
                                            </div>
                                        </div>
                                    </div>

                                    <input type="hidden" name="idClient" value="<%= client.getId()%>">    
                                    <input type="hidden" name="action" value="ajoutContrat">
                                    <div class="row"> </div>
                                    <div class="col-sm-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-sm-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-sm-offset-3">Valider</button>
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
