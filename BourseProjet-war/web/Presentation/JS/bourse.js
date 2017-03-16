function majAffichageGroupeSection() {
    i = document.formulaireAjoutEmploye.fonctionEmploye.selectedIndex;
    if (i == 1) {
        document.getElementById('listeChefsGroupe').style.display = 'block';
        document.getElementById('listeChefsSection').style.display = 'none';
        document.getElementById('listeNiveau').style.display = 'block';
        document.getElementById('selectChefsGroupe').required = true;
        document.getElementById('selectChefsSection').required = false;
        document.getElementById('selectNiveau').required = true;
    } else if (i == 2) {
        document.getElementById('listeChefsGroupe').style.display = 'none';
        document.getElementById('listeChefsSection').style.display = 'block';
        document.getElementById('listeNiveau').style.display = 'none';
        document.getElementById('selectChefsSection').required = true;
        document.getElementById('selectChefsGroupe').required = false;
        document.getElementById('selectNiveau').required = false;
    } else if (i == 3 || i == 4 || i == 5 || i == 6) {
        document.getElementById('listeChefsGroupe').style.display = 'none';
        document.getElementById('listeChefsSection').style.display = 'none';
        document.getElementById('listeNiveau').style.display = 'none';
        document.getElementById('selectChefsSection').required = false;
        document.getElementById('selectChefsGroupe').required = false;
        document.getElementById('selectNiveau').required = false;
    }

}

function majAffichageTypeClient() {
                i = document.formulaireAjoutClient.typeClient.selectedIndex;
                if (i == 1) {
                    document.getElementById('champsParticulier').style.display = 'block';
                    document.getElementById('champsEntreprise').style.display = 'none';
                    document.getElementById('nomClient').required = true;
                    document.getElementById('prenomClient').required = true;
                    document.getElementById('dateNaisClient').required = true;
                    document.getElementById('lieuNaisClient').required = true;
                    document.getElementById('siret').required = false;
                    document.getElementById('nomEntreprise').required = false;
                    document.getElementById('formeEntreprise').required = false;
                    document.getElementById('contactEntreprise').required = false;
                    document.getElementById('tphContactEntreprise').required = false;
                } else if (i == 2) {
                    document.getElementById('champsParticulier').style.display = 'none';
                    document.getElementById('champsEntreprise').style.display = 'block';
                    document.getElementById('nomClient').required = false;
                    document.getElementById('prenomClient').required = false;
                    document.getElementById('dateNaisClient').required = false;
                    document.getElementById('lieuNaisClient').required = false;
                    document.getElementById('siret').required = true;
                    document.getElementById('nomEntreprise').required = true;
                    document.getElementById('formeEntreprise').required = true;
                    document.getElementById('contactEntreprise').required = true;
                    document.getElementById('tphContactEntreprise').required = true;
                }
            }

 function verify(pwd, pwdConfirm)
 {
  var confirmation=false
   if (pwd.value=='')
   {
    alert("Veuillez entrer votre mot de passe dans le premier champ!")
    pwd.focus()
   }
   else if (pwdConfirm.value=='')
   {
    alert("Veuillez confirmer votre mot de passe dans le second champ!")
    pwdConfirm.focus()
   }

   else if (pwd.value!=pwdConfirm.value)
   {
    alert("Les deux mots de passe ne condordent pas")
    pwd.select()
   }
   else
   confirmation=true
   return confirmation
 }