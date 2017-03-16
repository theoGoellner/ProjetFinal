package com.bourse.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Définition de la Classe Marché qui contient comme attributs
 *      id                  : id du marché
 *      nom                 : nom du marché 
 *      typeMarché          : type auquel le marché appartient
 */

@Entity
public class Marche implements Serializable {
    
    @OneToMany (mappedBy="leMarche")
    private List<Titre> lesTitres;

    public List<Titre> getLesTitres() {
        return lesTitres;
    }

    public void setLesTitres(List<Titre> lesTitres) {
        this.lesTitres = lesTitres;
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
        if (!(object instanceof Marche)) {
            return false;
        }
        Marche other = (Marche) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Marche[ id=" + id + " ]";
    }
    
    private String nom;

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    private String typeMarche;

    /**
     * Get the value of typeMarche
     *
     * @return the value of typeMarche
     */
    public String getTypeMarche() {
        return typeMarche;
    }

    /**
     * Set the value of typeMarche
     *
     * @param typeMarche new value of typeMarche
     */
    public void setTypeMarche(String typeMarche) {
        this.typeMarche = typeMarche;
    }
}
