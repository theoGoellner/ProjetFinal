

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="controllerFrontOffice?action=accueil">MARTIN & CO</a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="controllerFrontOffice?action=accueil">Accueil</a></li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mon compte<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="controllerCommun?action=formModifierClient">Modifier mes infos</a></li>
                        <li><a href="controllerFrontOffice?action=formInitPwd">Changer de mot de passe</a></li>
                        <li><a href="controllerCommun?action=deconnexion">Se déconnecter</a></li>                            
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Courtages<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="controllerCommun?action=selectionTitres">Consulter les courtages</a></li>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mes portefeuilles<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="controllerCommun?action=afficherPortefeuillesClient">Consulter mes portefeuilles</a></li>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mes versements<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="controllerCommun?action=historiqueVersementsClient">Historique de mes versements</a></li>
                    </ul>
                </li>

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

        </div>
    </div>
</nav>
