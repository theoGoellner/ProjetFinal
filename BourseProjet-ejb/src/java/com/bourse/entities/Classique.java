package com.bourse.entities;

import com.bourse.enumeration.EnumNiveauGestionCompteCalssique;
import com.bourse.enumeration.EnumTypeGestCompteClassique;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Définition de la Classe Classique qui contient comme attributs
 *      EnumTypeGestCompteClassique         : secteur d'activité de l'action (banque, internet, ESN, luxe ...)
 *      EnumNiveauGestionCompteCalssique    : valeur de l'action
 *      ChargeCompte                        : indice principal de l'action
 *      valeurMax                           : place de cotation de l'action
 *      pourcentageMax                      : determine si PEA ou non
 *      montant                             : determine si SRV ou non
 */

@Entity
public class Classique extends PorteFeuille implements Serializable {

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
        if (!(object instanceof Classique)) {
            return false;
        }
        Classique other = (Classique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Classique[ id=" + id + " ]";
    }

    private EnumTypeGestCompteClassique type;
    /**
     * Get the value of type
     * @return the value of type
     */
    public EnumTypeGestCompteClassique getType() {
        return type;
    }

    /**
     * Set the value of type
     * @param type new value of type
     */
    public void setType(EnumTypeGestCompteClassique type) {
        this.type = type;
    }

    private EnumNiveauGestionCompteCalssique niveauGestion;
    /**
     * Get the value of niveauGestion
     * @return the value of niveauGestion
     */
    public EnumNiveauGestionCompteCalssique getNiveauGestion() {
        return niveauGestion;
    }
    /**
     * Set the value of niveauGestion
     * @param niveauGestion new value of niveauGestion
     */
    public void setNiveauGestion(EnumNiveauGestionCompteCalssique niveauGestion) {
        this.niveauGestion = niveauGestion;
    }

    private String ChargeCompte;
    /**
     * Get the value of ChargeCompte
     * @return the value of ChargeCompte
     */
    public String getChargeCompte() {
        return ChargeCompte;
    }
    /**
     * Set the value of ChargeCompte
     * @param ChargeCompte new value of ChargeCompte
     */
    public void setChargeCompte(String ChargeCompte) {
        this.ChargeCompte = ChargeCompte;
    }

    private Double valeurMax;
    /**
     * Get the value of valeurMax
     * @return the value of valeurMax
     */
    public Double getValeurMax() {
        return valeurMax;
    }
    /**
     * Set the value of valeurMax
     * @param valeurMax new value of valeurMax
     */
    public void setValeurMax(Double valeurMax) {
        this.valeurMax = valeurMax;
    }

    private Double pourcentageMax;
    /**
     * Get the value of pourcentageMax
     * @return the value of pourcentageMax
     */
    public Double getPourcentageMax() {
        return pourcentageMax;
    }
    /**
     * Set the value of pourcentageMax
     * @param pourcentageMax new value of pourcentageMax
     */
    public void setPourcentageMax(Double pourcentageMax) {
        this.pourcentageMax = pourcentageMax;
    }   
}
