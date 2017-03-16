
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css" />
        <link type="text/css" href="Presentation/CSS/bootstrap.css" rel="stylesheet">
        <link type="text/css" href="Presentation/CSS/bootstrap.min.css" rel="stylesheet">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>
        <title> Accueil </title>

    </head>
    <body>
        <%@include  file="../jsp_commun/menuBackOffice.jsp" %>
       
        <div class="container-fluid text-center col-sm-offset-2">
            <div class="row content">

                <div class="col-sm-10 text-left"> 
                    <h1> Bienvenue sur l'espace Employé </h1>
                   
                    
                    <%@include  file="../jsp_commun/footer.jsp" %>
                </div>
                
                <div class="col-sm-2 sidenav">
                    <div class="well ">
                                               
                    </div>
                </div>
                
            </div>
        </div>
    </body>
</html>


