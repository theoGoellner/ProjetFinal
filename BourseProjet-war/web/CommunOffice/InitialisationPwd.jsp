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
        <jsp:useBean id="identification" scope="session" class="com.bourse.entities.Identification"></jsp:useBean>

            <title>Initialisation mot de passe </title>       
        </head>
        <body>
            
        <%
            Identification ident =(Identification) session.getAttribute("identification");
                System.out.println("cocoo"+ident.getLogin());
           
        %>
        <%@include  file="../jsp_commun/menuBackOffice.jsp" %>
        
        
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">
                <div class="col-sm-10 text-left"> 
                    <%  String attribut = (String) request.getAttribute("message");
                        if (attribut.length() > 8) {%>
                    <div class="alert alert-info">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong> <% out.println(attribut); %> </strong>
                    </div> 
                    <% }%>
                    <div class="row">
                        <div class="col-lg-9 well col-sm-offset-2"> 
                            <form class="form-horizontal" method="post" action="controllerCommun" onSubmit="return verify(this.newPwd, this.newPwdConfirm)">
                                <fieldset>
                                    <legend>Réinitialisation du mot de passe utilisateur </legend>
                                    <div class="row"><p>     </p></div>

                                    <div class="form-group">
                                        <label for="loginUser" class="col-lg-3 control-label"> Identifiant </label>
                                        <div class="col-lg-9">
                                            <input type="text" class="form-control" name="loginUser" placeholder="Saisir votre login" required>
                                        </div>
                                    </div>        
                                    <div class="form-group">
                                        <label for="pwd" class="col-lg-3 control-label">Ancien mot de passe </label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="pwd" placeholder="Saisir votre ancien mot de passe" required>
                                        </div>
                                    </div>  
                                    <div class="form-group">
                                        <label for="newPwd" class="col-lg-3 control-label">Nouveau mot de passe </label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="newPwd" placeholder="Saisir nouveau mot de passe" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="newPwdConfirm" class="col-lg-3 control-label">Nouveau mot de passe </label>
                                        <div class="col-lg-9">
                                            <input type="password" class="form-control" name="newPwdConfirm" placeholder="Confirmer votre nouveau mot de passe" required>
                                        </div>
                                    </div> 

                                    <input type="hidden" name="action" value="pwdInit">
                                    <div class="row"> </div>
                                    <div class="col-sm-offset-4 col-lg-9">
                                        <button type="reset" class="btn btn-info col-sm-offset-1">Annuler</button>
                                        <button type="submit" class="btn btn-info col-sm-offset-3">Valider</button>
                                    </div>
                                </fieldset>

                            </form>
                        </div>
                    </div>
                    <%@include  file="../jsp_commun/footer.jsp" %>
                </div>
                <%@include  file="../jsp_commun/userEncours.jsp" %>
            </div>
        </div>
    </body>
</html>

