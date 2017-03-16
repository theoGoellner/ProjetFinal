<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bourse.enumeration.EnumRoleEmploye"%>
<%@page import="com.bourse.entities.Employe"%>
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
        <jsp:useBean id="ListeDesEmployes" scope="request" class="java.util.List"></jsp:useBean>

            <title>Nouvel Employé</title>       
        </head>
        <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        SimpleDateFormat sdf = new SimpleDateFormat("dd - MM - yyyy hh:mm:ss");
                        List<Employe> lesEmployes = ListeDesEmployes;
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% } %>
                    <div class="row">
                        <div class="col-lg-9 well col-sm-offset-2"> 
                            <form name="formulaireAjoutEmploye" class="form-horizontal" role="form" method="get" action="controllerBackOffice" onSubmit="return verify(this.pwd, this.pwdConfirm)">


                                <fieldset>
                                    <legend>Création d'un nouvel employé</legend>
                                    <div class="row"><p>     </p></div>

                                    <div class="form-group">
                                        <label for="fonctionEmploye" class="col-lg-3 control-label">Fonction occupée</label>
                                        <div class="col-lg-9">
                                            <select id="fonctionEmploye" class="form-control" name="fonctionEmploye" onchange="majAffichageGroupeSection()" required>
                                                <option value="" selected="selected"> </option>                                                                                        
                                                <option value="Courtier">Courtier</option>
                                                <option value="ChefGroupe">Chef de groupe</option>
                                                <option value="ChefSection">Chef de section</option>  
                                                <option value="Surveillant">Surveillant</option>
                                                <option value="ChefSalle">Responsable de Salle</option>                                         
                                                <option value="Administrateur">Administrateur</option>
                                            </select>
                                        </div> 
                                    </div>

                                    <div id="listeChefsSection" class="form-group" style="display:none">
                                        <label for="respSection" class="col-lg-3 control-label">Responsables de section</label>
                                        <div class="col-lg-9">
                                            <select id="selectChefsSection" class="form-control" name="idRespSection">
                                                <option value="" selected="selected"> </option> 
                                                <%
                                                    for (Employe emp : lesEmployes) {
                                                        if (emp.getRole() == EnumRoleEmploye.ChefSection) {%>
                                                <option  value="<%=emp.getId()%>"> <%= emp.getNom() + " " + emp.getPrenom()%></option>
                                                <%}
                                                }%>   
                                            </select>
                                        </div> 
                                    </div>       

                                    <div id="listeChefsGroupe" class="form-group" style="display:none">
                                        <label for="respGroupe" class="col-lg-3 control-label">Responsables de groupe</label>
                                        <div class="col-lg-9">
                                            <select id="selectChefsGroupe" class="form-control" name="idRespGroupe">
                                                <option value="" selected="selected"> </option> 
                                                <%
                                                    for (Employe emp : lesEmployes) {
                                                        if (emp.getRole() == EnumRoleEmploye.ChefGroupe) {%>
                                                <option  value="<%=emp.getId()%>"> <%= emp.getNom() + " " + emp.getPrenom()%></option>
                                                <%}
                                                }%>   
                                            </select>
                                        </div> 
                                    </div>                                            

                                    <div class="form-group">
                                        <label for="nomEmploye" class="col-lg-3 control-label">Nom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="nomEmploye" placeholder="Saisir le nom de l'employé" required>
                                        </div>
                                    </div>        
                                    <div class="form-group">
                                        <label for="prenomEmploye" class="col-lg-3 control-label">Prénom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="prenomEmploye" placeholder="Saisir le prenom de l'employé" required>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="dateEmbauche" class="col-lg-3 control-label">Date Embauche</label>
                                        <div class="col-lg-9">
                                            <input type="date" class="datepicker form-control" data-date-format="mm/dd/yyyy" name="dateEmbauche" required>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="emailEmploye" class="col-lg-3 control-label">E-mail</label>
                                        <div class="col-lg-9">
                                            <input type="email" class="form-control" name="emailEmploye" placeholder="Saisir l'email de l'employé " required>
                                        </div>
                                    </div>  
                                    <div id="listeNiveau" class="form-group" style="display:none">
                                        <label for="niveauEmploye" class="col-lg-3 control-label">Niveau</label>
                                        <div class="col-lg-9">
                                            <select id="selectNiveau" class="form-control" name="niveauEmploye">
                                                <option value="" selected="selected"> </option> 
                                                <option value="1">Niveau 1 (le plus élevé)</option>
                                                <option value="2">Niveau 2</option>
                                                <option value="3">Niveau 3</option>
                                                <option value="4">Niveau 4</option>
                                                <option value="5">Niveau 5 (le moins élevé)</option>
                                            </select>
                                        </div> 
                                    </div>                                      

                                    <div class="form-group">
                                        <label for="login" class="col-lg-3 control-label">Identifiant</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="login" placeholder="saisir l'identifiant de l'employé" required>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="pwd" class="col-lg-3 control-label">Mot de passe</label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="pwd" placeholder="saisir le mot de passe de l'employé" required>
                                        </div>
                                    </div>                                                              
                                    <div class="form-group">
                                        <label for="pwdConfirm" class="col-lg-3 control-label">Confirmation du mot de passe </label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="pwdConfirm" placeholder="confirmation du mot de passe" required>
                                        </div>
                                    </div> 
                                    <input type="hidden" name="action" value="ajoutEmploye">
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
                            <a href="#info" class="btn btn-primary" data-toggle="collapse">Afficher la liste des employés</a>
                        </div>
                    </div>
                    <div id="info" class="collapse">
                        <br>
                        <%if (lesEmployes.isEmpty())
                                out.println("La liste des employés est vide !");
                            else {%>
                        <table class="table table-hover">
                            <thead>
                                <tr> 
                                    <td> Nom </td ><td> Prenom </td> <td> Fonction </td> <td> Email </td> <td> Date d'embauche </td> <td> Niveau </td><td> Responsable </td><td> Archiver </td>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Employe emp : lesEmployes) {%>
                                <tr> 
                                    <td > <%= emp.getNom()%> </td>
                                    <td > <%= emp.getPrenom()%> </td>
                                    <td > <%= emp.getRole()%> </td>
                                    <td > <%= emp.getEmail()%> </td>
                                    <td> <%= sdf.format(emp.getDateEmbauche())%> </td>
                                    <td > <%= emp.getNiveau()%> </td>                                    
                                    <td> <% if (emp.getResponsable() == null)
                                            out.println("-----");
                                        else {%> <%= emp.getResponsable().getNom() + " " + emp.getResponsable().getPrenom()%> <%}%> 
                                    </td> 
                                    <td ><a href="controllerBackOffice?action=archiverEmploye&idEmploye=<%= emp.getId()%>"> <span class="glyphicon glyphicon-trash"></span> Archiver </a>
                                    </td>
                                    <td ><a href="controllerBackOffice?action=formModifierEmploye&idEmploye=<%= emp.getId()%>"> Modifier </a>
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
