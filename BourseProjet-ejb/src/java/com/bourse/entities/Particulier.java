package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class Particulier extends Client implements Serializable {

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
        if (!(object instanceof Particulier)) {
            return false;
        }
        Particulier other = (Particulier) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Particulier[ id=" + id + " ]";
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
    private Date dateNais;
    /**
     * Get the value of dateNais
     * @return the value of dateNais
     */
    public Date getDateNais() {
        return dateNais;
    }
    /**
     * Set the value of dateNais
     * @param dateNais new value of dateNais
     */
    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

        private String lieuNaissance;

    /**
     * Get the value of lieuNaissance
     *
     * @return the value of lieuNaissance
     */
    public String getLieuNaissance() {
        return lieuNaissance;
    }

    /**
     * Set the value of lieuNaissance
     *
     * @param lieuNaissance new value of lieuNaissance
     */
    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }
}
