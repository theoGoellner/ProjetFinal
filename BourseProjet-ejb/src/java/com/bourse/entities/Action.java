package com.bourse.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Définition de la Classe Action qui contient comme attributs
 *      secteurActivite       : secteur d'activité de l'action (banque, internet, ESN, luxe ...)
 *      partCapital           : valeur de l'action
 *      indicePrincipal       : indice principal de l'action
 *      placeCotation         : place de cotation de l'action
 *      indicateurPEA         : determine si PEA ou non
 *      indicateurSRD         : determine si SRV ou non
 */

@Entity
public class Action extends Titre implements Serializable {

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
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Action[ id=" + id + " ]";
    }
    
    private String secteurActivite;

    /**
     * Get the value of secteurActivite
     *
     * @return the value of secteurActivite
     */
    public String getSecteurActivite() {
        return secteurActivite;
    }

    /**
     * Set the value of secteurActivite
     *
     * @param secteurActivite new value of secteurActivite
     */
    public void setSecteurActivite(String secteurActivite) {
        this.secteurActivite = secteurActivite;
    }

    private Double partCapital;

    /**
     * Get the value of partCapital
     *
     * @return the value of partCapital
     */
    public Double getPartCapital() {
        return partCapital;
    }

    /**
     * Set the value of partCapital
     *
     * @param partCapital new value of partCapital
     */
    public void setPartCapital(Double partCapital) {
        this.partCapital = partCapital;
    }

    private int indicePrincipal;

    /**
     * Get the value of indicePrincipal
     *
     * @return the value of indicePrincipal
     */
    public int getIndicePrincipal() {
        return indicePrincipal;
    }

    /**
     * Set the value of indicePrincipal
     *
     * @param indicePrincipal new value of indicePrincipal
     */
    public void setIndicePrincipal(int indicePrincipal) {
        this.indicePrincipal = indicePrincipal;
    }

    private String placeCotation;

    /**
     * Get the value of placeCotation
     *
     * @return the value of placeCotation
     */
    public String getPlaceCotation() {
        return placeCotation;
    }

    /**
     * Set the value of placeCotation
     *
     * @param placeCotation new value of placeCotation
     */
    public void setPlaceCotation(String placeCotation) {
        this.placeCotation = placeCotation;
    }

    private Boolean indicateurPEA;

    /**
     * Get the value of indicateurPEA
     *
     * @return the value of indicateurPEA
     */
    public Boolean isIndicateurPEA() {
        return indicateurPEA;
    }

    /**
     * Set the value of indicateurPEA
     *
     * @param indicateurPEA new value of indicateurPEA
     */
    public void setIndicateurPEA(Boolean indicateurPEA) {
        this.indicateurPEA = indicateurPEA;
    }

    private Boolean indicateurSRD;

    /**
     * Get the value of indicateurSRD
     *
     * @return the value of indicateurSRD
     */
    public Boolean isIndicateurSRD() {
        return indicateurSRD;
    }

    /**
     * Set the value of indicateurSRD
     *
     * @param indicateurSRD new value of indicateurSRD
     */
    public void setIndicateurSRD(Boolean indicateurSRD) {
        this.indicateurSRD = indicateurSRD;
    }

}
