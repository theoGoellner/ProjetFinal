<%@page import="com.bourse.enumeration.EnumRoleEmploye"%>
<%@page import="com.bourse.entities.Employe"%>
<%@page import="com.bourse.entities.Particulier"%>
<%@page import="java.text.SimpleDateFormat"%>
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

        <title>Modifier Employe</title>       
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
                    <%  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");                        
                        Employe empl = (Employe) request.getAttribute("employe");
                    %>
                    <div class="row">
                        <div class="col-lg-8 well col-lg-offset-2"> 
                            <form name="formulaireModifParticulier" class="form-horizontal" role="form" method="get" action="controllerBackOffice">
                                <div class="modal-header">
                                    <h1 class="text-center">Modification d'un employe</h1>
                                </div>
                                <div class="row"><p>     </p></div>  
                                <div class="form-group">
                                        <label for="nomEmploye" class="col-lg-3 control-label">Nom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="nomEmploye" value="<%= empl.getNom() %>" required>
                                        </div>
                                    </div>        
                                    <div class="form-group">
                                        <label for="prenomEmploye" class="col-lg-3 control-label">Prénom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="prenomEmploye" value="<%= empl.getPrenom() %>" required>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="dateEmbauche" class="col-lg-3 control-label">Date Embauche</label>
                                        <div class="col-lg-9">
                                            <input type="date" class="form-control" name="dateEmbauche" value="<%= sdf.format(empl.getDateEmbauche()) %>" required>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="emailEmploye" class="col-lg-3 control-label">E-mail</label>
                                        <div class="col-lg-9">
                                            <input type="email" class="form-control" name="emailEmploye" value="<%= empl.getEmail() %>" required>
                                        </div>
                                    </div>                                        
                                
                                <% if(empl.getRole().equals(EnumRoleEmploye.Courtier)) {%>
                                <div class="form-group">
                                    <label for="niveauEmploye" class="col-lg-3 control-label">Niveau</label>
                                    <div class="col-lg-9">
                                        <select class="form-control" name="niveauEmploye" required>
                                            <option value=""> </option> 
                                            <% for (int i = 1; i <= 5; i++) { 
                                            if (i==empl.getNiveau()) { %>
                                                <option value="<%= i %>" selected="selected" > Niveau <%= " "+i %></option>
                                            <%} else {%> --%>
                                                <option value="<%= i %>" > Niveau <%= " "+i %></option>
                                            <% }} %>
                                        </select>
                                    </div> 
                                </div>  
                                <%}%>
                                                                                
                                <input type="hidden" name="idEmploye" value="<%= empl.getId() %>">

                                <input type="hidden" name="action" value="modifierEmploye">
                                <div class="row"> </div>
                                <div class="col-lg-offset-4 col-lg-8">
                                    <button type="reset" class="btn btn-primary col-lg-offset-1">Annuler</button>
                                    <button type="submit" class="btn btn-primary col-lg-offset-3">Modifier</button>
                                </div>
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
