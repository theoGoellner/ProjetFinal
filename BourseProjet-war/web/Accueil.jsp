<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css" />
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>
        <title> Accueil </title>
    </head>
    <body>
        <%@include  file="jsp_commun/menu.jsp" %>

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
                    
                    <h1 align='center'> Bienvenue chez MARTIN & CO </h1>  
                    
                    <div align="middle">
                        <iframe width="800" height="600" src="https://app.powerbi.com/view?r=eyJrIjoiYjVkYTQ4ZWMtOWY2Ny00ZDU2LTkwYWYtMmIxMTg1NDYzOGQ0IiwidCI6IjAwMzJhZjMyLTBhZTAtNDMwMC1iZjY3LTgwOWM2MjA4NGU2NiIsImMiOjh9" frameborder="0" allowFullScreen="true"></iframe>
                        <iframe width="800" height="600" src="https://app.powerbi.com/view?r=eyJrIjoiYjVkYTQ4ZWMtOWY2Ny00ZDU2LTkwYWYtMmIxMTg1NDYzOGQ0IiwidCI6IjAwMzJhZjMyLTBhZTAtNDMwMC1iZjY3LTgwOWM2MjA4NGU2NiIsImMiOjh9" frameborder="0" allowFullScreen="true"></iframe>
                    </div>
                    
                    <%@include  file="jsp_commun/footer.jsp" %>
                </div>

            </div>
        </div>
    </body>
</html>


