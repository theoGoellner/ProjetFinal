<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="Presentation/CSS/bootstrap.css" />
        <link type="text/css" href="Presentation/CSS/bootstrap.min.css" rel="stylesheet">
        <script src="Presentation/JS/jquery.min.js"></script>
        <script src="Presentation/JS/bootstrap.min.js"></script>
        
        <title>Authentification</title>
    </head>
    <body>
         <% String attribut = (String) request.getAttribute("message"); %>
        <div id="loginModal" class="modal show" tabindex="-1" role="dialog" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h1 class="text-center">Authentification</h1>
                    </div>
                    <div class="modal-body">
                        <form class="form col-md-12 center-block" method="get" action="controllerCommun">
                            <fieldset> 
                                <div class="form-group">
                                    <input type="text" name="login" value="" class="form-control input-lg" placeholder="Login" size="20" maxlength="20"/>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="pwd" value="" class="form-control input-lg" placeholder="Password" size="20" maxlength="20"/>
                                </div>
                                <input type="hidden" name="action" value="authentification">
                            </fieldset>

                            <div class="form-group">
                                <button class="btn btn-primary btn-lg btn-block">S'authentifier</button>
                            </div>
                        </form>
                    </div>
                    
                    <div class="modal-footer">
                        <div class="col-md-12 text-danger text-center">
                            <P><strong><% if (attribut.length()>10) out.println(attribut); %> </strong> </P>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
        <script src="JS/bootstrap.min.js"></script>
    </body>
</html>