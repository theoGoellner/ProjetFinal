package com.bourse.entities;

import com.bourse.enumeration.EnumRoleEmploye;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Définition de la classe Employé qui contient comme attributs : 
 *      id                  : identifiant de l'Employé
 *      nom                 : nom de l'employé
 *      prenom              : prenom de l'employé 
 *      dateEmbauche        : date de l'embauche de l'employe  
 *      dateFinContrat      : date de finContrat
 *      telephone           : telephne de l'employé
 *      EnActivite          : Etat de l'employé (en activité ou licencié)
 *      niveau              : niveau d'importance du client varie de 1 à 5 
 *                            (1 pour les très importants client et 5 pour les moins important)
 *      est_responsable     : le responsable de l'employé
 *      subordonnes         : pour un reponsable, subordonnés contient la liste des courtiers sous sa responsabilité
 *      listClients         : pour un courtier, listClients contient sa liste des clients 
 */

@Entity
public class Employe implements Serializable {

    @OneToMany(mappedBy = "unEmploye")
    private List<JournalActionSurOperation> lesJournalActionsSurOperation;

    public List<JournalActionSurOperation> getLesJournalActionsSurOperation() {
        return lesJournalActionsSurOperation;
    }

    public void setLesJournalActionsSurOperation(List<JournalActionSurOperation> lesJournalActionsSurOperation) {
        this.lesJournalActionsSurOperation = lesJournalActionsSurOperation;
    }   

    @ManyToOne
    private Employe responsable;
    public Employe getResponsable() {
        return responsable;
    }
    public void setResponsable(Employe responsable) {
        this.responsable = responsable;
    }
    
    @OneToMany(mappedBy="responsable")
    private List<Employe> subordonnes;
    public List<Employe> getSubordonnes() {
        return subordonnes;
    }
    public void setSubordonnes(List<Employe> subordonnes) {
        this.subordonnes = subordonnes;
    }     
    
    @OneToMany(mappedBy="courtier")
    private List<Client> listClients;
    public List<Client> getListClients() {
        return listClients;
    }
    public void setListClients(List<Client> listClients) {
        this.listClients = listClients;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employe)) {
            return false;
        }
        Employe other = (Employe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Employe[ id=" + id + " ]";
    }

    private String nom;
    /**
     * Get the value of nom
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }
    /**
     * Set the value of nom
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    private String prenom;
    /**
     * Get the value of prenom
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }
    /**
     * Set the value of prenom
     * @param prenom new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmbauche;
    /**
     * Get the value of dateEmbauche
     * @return the value of dateEmbauche
     */
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    /**
     * Set the value of dateEmbauche
     * @param dateEmbauche new value of dateEmbauche
     */
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }  
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFinContrat;
    /**
     * Get the value of dateFinContrat
     * @return the value of dateFinContrat
     */
    public Date getDateFinContrat() {
        return dateFinContrat;
    }
    /**
     * Set the value of dateFinContrat
     * @param dateFinContrat new value of dateFinContrat
     */
    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }
    
    private int niveau;
    /**
     * Get the value of niveau
     * @return the value of niveau
     */
    public int getNiveau() {
        return niveau;
    }
    /**
     * Set the value of niveau
     * @param niveau new value of niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
    private EnumRoleEmploye role;

    /**
     * Get the value of role
     * @return the value of role
     */
    public EnumRoleEmploye getRole() {
        return role;
    }

    /**
     * Set the value of role
     * @param role new value of role
     */
    public void setRole(EnumRoleEmploye role) {
        this.role = role;
    }

    private String email;

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    private String laSection;

    public String getLaSection() {
        return laSection;
    }

    public void setLaSection(String laSection) {
        this.laSection = laSection;
    }

    private String groupe;

    /**
     * Get the value of groupe
     *
     * @return the value of groupe
     */
    public String getGroupe() {
        return groupe;
    }

    /**
     * Set the value of groupe
     *
     * @param groupe new value of groupe
     */
    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

}
