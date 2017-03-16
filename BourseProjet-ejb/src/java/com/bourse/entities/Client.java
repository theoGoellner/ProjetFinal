package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Définition de la classe Client qui contient comme attributs : 
 *      id          : identifiant du client
 *      téléphone   : numéro de téléphone du client
 *      e-mail      : l'e-mail du client 
 *      niveau      : niveau d'importance du client varie de 1 à 5 
 *                    (1 pour les très importants client et 5 pour les moins important)
 *      Employe     : emplye de la bourse qui gère son portefueil 
 */
@Entity
@Inheritance
    (strategy=InheritanceType.JOINED)
public class Client implements Serializable {

    @OneToMany(mappedBy = "leClient")
    private List<Versement> lesVersements;

    public List<Versement> getLesVersements() {
        return lesVersements;
    }

    public void setLesVersements(List<Versement> lesVersements) {
        this.lesVersements = lesVersements;
    }

    @OneToMany(mappedBy = "leClient")
    private List<Contrat> lesContrats;

    public List<Contrat> getLesContrats() {
        return lesContrats;
    }

    public void setLesContrats(List<Contrat> lesContrats) {
        this.lesContrats = lesContrats;
    }

    @ManyToOne
    private Employe courtier;
    public Employe getCourtier() {
        return courtier;
    }
    public void setCourtier(Employe courtier) {
        this.courtier = courtier;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Client[ id=" + id + " ]";
    }
    
    private String telephone;
    /**
     * Get the value of telephone
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }
    /**
     * Set the value of telephone
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    
    private String mail;
    /**
     * Get the value of mail
     * @return the value of mail
     */
    public String getMail() {
        return mail;
    }
    /**
     * Set the value of mail
     * @param mail new value of mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    private String adresse;
    /**
     * Get the value of adresse
     * @return the value of adresse
     */
    public String getAdresse() {
        return adresse;
    }
    /**
     * Set the value of adresse
     * @param adresse new value of adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateArchivage;

    /**
     * Get the value of dateArchivage
     *
     * @return the value of dateArchivage
     */
    public Date getDateArchivage() {
        return dateArchivage;
    }

    /**
     * Set the value of dateArchivage
     *
     * @param dateArchivage new value of dateArchivage
     */
    public void setDateArchivage(Date dateArchivage) {
        this.dateArchivage = dateArchivage;
    }

}
