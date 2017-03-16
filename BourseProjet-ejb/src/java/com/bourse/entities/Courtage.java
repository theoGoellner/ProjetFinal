package com.bourse.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Définition de la Classe Courtage qui contient comme attributs
 *      cours           : valeur du cours
 */

@Entity
public class Courtage implements Serializable {

    @ManyToOne
    private Titre leTitre;

    public Titre getLeTitre() {
        return leTitre;
    }

    public void setLeTitre(Titre leTitre) {
        this.leTitre = leTitre;
    }
    
    
    @ManyToOne
    private DateCourtage dateCourtage;

    public DateCourtage getDateCourtage() {
        return dateCourtage;
    }

    public void setDateCourtage(DateCourtage dateCourtage) {
        this.dateCourtage = dateCourtage;
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
        if (!(object instanceof Courtage)) {
            return false;
        }
        Courtage other = (Courtage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Courtage[ id=" + id + " ]";
    }
    
    private Double cours;

    /**
     * Get the value of cours
     *
     * @return the value of cours
     */
    public Double getCours() {
        return cours;
    }

    /**
     * Set the value of cours
     *
     * @param cours new value of cours
     */
    public void setCours(Double cours) {
        this.cours = cours;
    }

    
}
