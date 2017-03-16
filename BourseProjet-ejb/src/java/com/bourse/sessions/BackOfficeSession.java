package com.bourse.sessions;

import com.bourse.entities.Client;
import com.bourse.entities.Contrat;
import com.bourse.entities.Employe;
import com.bourse.entities.Entreprise;
import com.bourse.entities.Particulier;
import com.bourse.entities.PorteFeuille;
import com.bourse.enumeration.EnumFormEntreprise;
import com.bourse.facades.ClientFacadeLocal;
import com.bourse.facades.ContratFacadeLocal;
import com.bourse.facades.EntrepriseFacadeLocal;
import com.bourse.facades.IdentificationFacadeLocal;
import com.bourse.facades.ParticulierFacadeLocal;
import com.bourse.facades.PorteFeuilleFacadeLocal;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class BackOfficeSession implements BackOfficeSessionLocal {

    @EJB
    private PorteFeuilleFacadeLocal porteFeuilleFacade;

    @EJB
    private ContratFacadeLocal contratFacade;

    @EJB
    private ClientFacadeLocal clientFacade;

    @EJB
    private EntrepriseFacadeLocal entrepriseFacade;

    @EJB
    private ParticulierFacadeLocal particulierFacade;

    @EJB
    private IdentificationFacadeLocal identificationFacade;

    
    
    // GESTION DES CLIENTS

    @Override
    public Particulier creationParticulier(String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau, Employe courtier) {
        return particulierFacade.creerParticulier(nom, prenom, dateNais, lieuNais, telephone, email, adresse, niveau, courtier);
    }

    @Override
    public Particulier rechercheParticulierParNomPrenom(String nom, String prenom) {
        return particulierFacade.rechercherParticulierParNomPrenom(nom, prenom);
    }
    
    @Override
    public void modificationParticulier(Particulier part, String nom, String prenom, Date dateNais, String lieuNais, String telephone, String email, String adresse, int niveau) {
        particulierFacade.modifierParticulier(part, nom, prenom, dateNais, lieuNais, telephone, email, adresse, niveau);
    }
    ///teste
    @Override
    public Entreprise creationEntreprise(String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau, Employe courtier) {
        return entrepriseFacade.creerEntreprise(siret, nomEntreprise, formeEntreprise, contact, tphContact, telephone, email, adresse, niveau, courtier);
    }

    @Override
    public Entreprise rechercheEntrepriseParSIRET(String siret) {
        return entrepriseFacade.rechercherEntrepriseParSIRET(siret);
    }
    
    @Override
    public void modificationEntreprise(Entreprise entr, String siret, String nomEntreprise, EnumFormEntreprise formeEntreprise, String contact, String tphContact, String telephone, String email, String adresse, int niveau) {
        entrepriseFacade.modifierEntreprise(entr, siret, nomEntreprise, formeEntreprise, contact, tphContact, telephone, email, adresse, niveau);
    }
    
    @Override
    public List<Particulier> getListeParticuliersActifs() {
        return particulierFacade.getListeParticuliersActifs();
    }
    
    @Override
    public List<Particulier> getListeParticuliersActifsParCourtier(Employe courtier) {
        return particulierFacade.getListeParticuliersActifsParCourtier(courtier);
    }

    @Override
    public List<Entreprise> getListeEntreprisesActives() {
        return entrepriseFacade.getListeEntreprisesActives();
    }
    
    @Override
    public List<Entreprise> getListeEntreprisesActivesParCourtier(Employe courtier) {
        return entrepriseFacade.getListeEntreprisesActivesParCourtier(courtier);
    }

    @Override
    public void archivageClient(Client client) {
        clientFacade.archiverClient(client);
        identificationFacade.archiverIdentification(identificationFacade.rechercherIdentParIDUserEtType(client.getId(), "Client"));
    }
    
    @Override
    public Client rechercheClientParID(Long idClient) {
        return clientFacade.rechercherClientParID(idClient);
    }

    @Override
    public List<Client> getListeClients() {
        return clientFacade.getListeClients();
    }

    @Override
    public List<Particulier> rechercheListeParticuliersParCourtierParNomPrenom(Employe courtier, String nom, String prenom) {
        return particulierFacade.rechercherListeParticuliersParCourtierParNomPrenom(courtier, nom, prenom);
    }

    @Override
    public List<Entreprise> rechercheListeEntreprisesParCourtierParNomPrenom(Employe courtier, String siret, String nom) {
        return entrepriseFacade.rechercheListeEntreprisesParCourtierParNomSiret(courtier, siret, nom);
    }

    
    // GESTION DES CONTRATS
    
    @Override
    public Contrat creationContrat(Date dateDebut, String rib, String typeContrat, Client cli) {
        return contratFacade.creerContrat(dateDebut, rib, typeContrat, cli);
    }

    @Override
    public Contrat rechercheContratParID(Long idContrat) {
        return contratFacade.rechercherContratParID(idContrat);
    }

    // GESTION DES PORTEFEULLLE

    @Override
    public PorteFeuille creationPorteFeuille(Double montantInitial, Contrat contrat) {
        return porteFeuilleFacade.creerPorteFeuille(montantInitial, contrat);
    }

    @Override
    public PorteFeuille recherchePorteFeuilleParID(Long idPorteFeuille) {
        return porteFeuilleFacade.rechercherPorteFeuilleParID(idPorteFeuille);
    }
 
}
