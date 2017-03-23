<%@page import="com.bourse.entities.Employe"%>
<jsp:useBean id="employe" scope="session" class="com.bourse.entities.Employe"></jsp:useBean> 
<%  Employe user = (Employe) session.getAttribute("employe"); %>
<div class="bs-component">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="controllerBackOffice?action=accueil">MARTIN & CO</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="controllerBackOffice?action=accueil">Accueil</a></li>                 
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mon compte<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerBackOffice?action=formInitPwd">Changer de mot de passe</a></li>
                            <li><a href="controllerCommun?action=deconnexion">Se déconnecter</a></li>                            
                        </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des clients<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerBackOffice?action=formAjoutClient">Nouveau client</a></li>
                            <li><a href="controllerBackOffice?action=gestionClientsCourtier">Gerer mes clients</a></li>
                            <li><a href="controllerBackOffice?action=formRechClientGestion">Rechercher un client</a></li>
                        </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des versements<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerBackOffice?action=formRechClientVersement">Nouveau versement</a></li>
                            <li><a href="controllerBackOffice?action=formRechClientVersement">Historique des versements</a></li>
                        </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des portefeuilles<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerBackOffice?action=formRechClientPF">Nouveau portefeuille</a></li>
                            <li><a href="controllerBackOffice?action=gestionClientsCourtier">Consulter les portefeuilles clients</a></li>
                        </ul>
                    </li>
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Gestion des titres<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerCommun?action=selectionTitres">Consulter les courtages</a></li>
                        </ul>
                    </li>
                    
                    <li class="active"><a href="controllerBackOffice?action=formAjoutEmploye">Gestion des employés</a></li>
                    
                    <%--
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#">Action</a></li>
                            <li><a href="#">Another action</a></li>
                            <li><a href="#">Something else here</a></li>
                            <li class="divider"></li>
                            <li><a href="#">Separated link</a></li>
                            <li class="divider"></li>
                            <li><a href="#">One more separated link</a></li>
                        </ul>
                    </li>
                    --%>
                </ul>

                
                <ul class="nav navbar-nav navbar-right">
                    <li class="active" > <a href="#"> Bonjour,<%= user.getNom() +" "+user.getPrenom() %></a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
