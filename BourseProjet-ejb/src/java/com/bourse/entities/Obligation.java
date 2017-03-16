package com.bourse.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Définition de la Classe Obligation qui contient comme attributs
 *      categorie                : categorie à laquelle l'obligation appartient
 *      prixEmission             : prix auquel l'obligation a été émise
 *      interetPaye              : montant des intérêts payés
 *      prixRemboursement        : prix de remboursement de l'obligation
 *      conditionRemboursement   : condition de reboursement de l'obligation
 */

@Entity
public class Obligation extends Titre implements Serializable {

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
        if (!(object instanceof Obligation)) {
            return false;
        }
        Obligation other = (Obligation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Obligation[ id=" + id + " ]";
    }
    
    private String categorie;

    /**
     * Get the value of categorie
     *
     * @return the value of categorie
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Set the value of categorie
     *
     * @param categorie new value of categorie
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    private Double prixEmission;

    /**
     * Get the value of prixEmission
     *
     * @return the value of prixEmission
     */
    public Double getPrixEmission() {
        return prixEmission;
    }

    /**
     * Set the value of prixEmission
     *
     * @param prixEmission new value of prixEmission
     */
    public void setPrixEmission(Double prixEmission) {
        this.prixEmission = prixEmission;
    }

    private Double interetPaye;

    /**
     * Get the value of interetPaye
     *
     * @return the value of interetPaye
     */
    public Double getInteretPaye() {
        return interetPaye;
    }

    /**
     * Set the value of interetPaye
     *
     * @param interetPaye new value of interetPaye
     */
    public void setInteretPaye(Double interetPaye) {
        this.interetPaye = interetPaye;
    }

    private Double prixRemboursement;

    /**
     * Get the value of prixRemboursement
     *
     * @return the value of prixRemboursement
     */
    public Double getPrixRemboursement() {
        return prixRemboursement;
    }

    /**
     * Set the value of prixRemboursement
     *
     * @param prixRemboursement new value of prixRemboursement
     */
    public void setPrixRemboursement(Double prixRemboursement) {
        this.prixRemboursement = prixRemboursement;
    }

    private String conditionRemboursement;

    /**
     * Get the value of conditionRemboursement
     *
     * @return the value of conditionRemboursement
     */
    public String getConditionRemboursement() {
        return conditionRemboursement;
    }

    /**
     * Set the value of conditionRemboursement
     *
     * @param conditionRemboursement new value of conditionRemboursement
     */
    public void setConditionRemboursement(String conditionRemboursement) {
        this.conditionRemboursement = conditionRemboursement;
    }
}
