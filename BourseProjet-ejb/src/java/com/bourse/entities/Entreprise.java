package com.bourse.entities;

import com.bourse.enumeration.EnumFormEntreprise;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Définition de la classe Entreprise - Client physique - qui hérite des propriétés de la classe Client : 
 *      SIRET        : numéro SIRET de l'entreprise
 *      raisonSociale: raison sociale de l'entreprise
 *      formeSociete : EnumListe des différentes Formes de la société 
 *      contact      : nom et prenom de la personne à contacter dans la société
 *      tphContact   : numéro de téléphone de la personne à contacter dans l'entreprise 
 */

@Entity
public class Entreprise extends Client implements Serializable {

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
        if (!(object instanceof Entreprise)) {
            return false;
        }
        Entreprise other = (Entreprise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Entreprise[ id=" + id + " ]";
    }

    @Column(nullable = false, unique = true)    
    private String Siret;
    /**
     * Get the value of Siret
     * @return the value of Siret
     */
    public String getSiret() {
        return Siret;
    }
    /**
     * Set the value of Siret
     * @param Siret new value of Siret
     */
    public void setSiret(String Siret) {
        this.Siret = Siret;
    }

    private EnumFormEntreprise formeSociete;
    /**
     * Get the value of formeSociete
     * @return the value of formeSociete
     */
    public EnumFormEntreprise getFormeSociete() {
        return formeSociete;
    }
    /**
     * Set the value of formeSociete
     * @param formeSociete new value of formeSociete
     */
    public void setFormeSociete(EnumFormEntreprise formeSociete) {
        this.formeSociete = formeSociete;
    }

    private String contact;
    /**
     * Get the value of contact
     * @return the value of contact
     */
    public String getContact() {
        return contact;
    }
    /**
     * Set the value of contact
     * @param contact new value of contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    
    private String tphContact;
    /**
     * Get the value of tphContact
     * @return the value of tphContact
     */
    public String getTphContact() {
        return tphContact;
    }
    /**
     * Set the value of tphContact
     * @param tphContact new value of tphContact
     */
    public void setTphContact(String tphContact) {
        this.tphContact = tphContact;
    }

    private String nomEntreprise;

    /**
     * Get the value of nomEntreprise
     *
     * @return the value of nomEntreprise
     */
    public String getNomEntreprise() {
        return nomEntreprise;
    }

    /**
     * Set the value of nomEntreprise
     *
     * @param nomEntreprise new value of nomEntreprise
     */
    public void setNomEntreprise(String nomEntreprise) {
        this.nomEntreprise = nomEntreprise;
    }

}
