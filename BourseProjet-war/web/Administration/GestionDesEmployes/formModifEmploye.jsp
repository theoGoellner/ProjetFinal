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
        <link type="text/css" href="Presentation/CSS/bootstrap.min.css" rel="stylesheet">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>

        <script src="Presentation/JS/bourse.js"></script>
        <jsp:useBean id="employe" scope="request" class="com.bourse.entities.Employe"></jsp:useBean> 

        <title>Modifier Employe</title>       
    </head>
    <body>
        <%@include  file="../../jsp_commun/menuBackOffice.jsp" %>
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");                        
                        Employe user = (Employe) request.getAttribute("employe");
                    %>
                    <div class="row">
                        <div class="col-sm-8 well col-sm-offset-2"> 
                            <form name="formulaireModifParticulier" class="form-horizontal" role="form" method="get" action="controllerBackOffice">
                                <div class="modal-header">
                                    <h1 class="text-center">Modification d'un employe</h1>
                                </div>
                                <div class="row"><p>     </p></div>  
                                <div class="form-group">
                                        <label for="nomEmploye" class="col-lg-3 control-label">Nom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="nomEmploye" value="<%= user.getNom() %>" required>
                                        </div>
                                    </div>        
                                    <div class="form-group">
                                        <label for="prenomEmploye" class="col-lg-3 control-label">Prénom</label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="prenomEmploye" value="<%= user.getPrenom() %>" required>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="dateEmbauche" class="col-lg-3 control-label">Date Embauche</label>
                                        <div class="col-lg-9">
                                            <input type="date" class="form-control" name="dateEmbauche" value="<%= sdf.format(user.getDateEmbauche()) %>" required>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <label for="emailEmploye" class="col-lg-3 control-label">E-mail</label>
                                        <div class="col-lg-9">
                                            <input type="email" class="form-control" name="emailEmploye" value="<%= user.getEmail() %>" required>
                                        </div>
                                    </div>                                        
                                
                                <% if(user.getRole().equals(EnumRoleEmploye.Courtier)) {%>
                                <div class="form-group">
                                    <label for="niveauEmploye" class="col-lg-3 control-label">Niveau</label>
                                    <div class="col-lg-9">
                                        <select class="form-control" name="niveauEmploye" required>
                                            <option value=""> </option> 
                                            <% for (int i = 1; i <= 5; i++) { 
                                            if (i==user.getNiveau()) { %>
                                                <option value="<%= i %>" selected="selected" > Niveau <%= " "+i %></option>
                                            <%} else {%> --%>
                                                <option value="<%= i %>" > Niveau <%= " "+i %></option>
                                            <% }} %>
                                        </select>
                                    </div> 
                                </div>  
                                <%}%>
                                                                                
                                <input type="hidden" name="idEmploye" value="<%= user.getId() %>">

                                <input type="hidden" name="action" value="modifierEmploye">
                                <div class="row"> </div>
                                <div class="col-sm-offset-4 col-sm-8">
                                    <button type="reset" class="btn btn-primary col-sm-offset-1">Annuler</button>
                                    <button type="submit" class="btn btn-primary col-sm-offset-3">Modifier</button>
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
