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
                <a class="navbar-brand" href="controllerBackOffice?action=accueil">BOURSE DE FRANCE </a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="controllerBackOffice?action=accueil">Accueil</a></li>                 
                    
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mon compte<span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="controllerCommun?action=formInitPwd">Changer de mot de passe</a></li>
                            <li><a href="controllerCommun?action=deconnexion">Se d�connecter</a></li>                            
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
                            <li><a href="">Nouveau portefeuille</a></li>
                            <li><a href="">Consulter les portefeuilles clients</a></li>
                        </ul>
                    </li>
                    
                    <li class="active"><a href="controllerBackOffice?action=formAjoutEmploye">Gestion des employ�s</a></li>
                    
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
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Rechercher</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Link</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
