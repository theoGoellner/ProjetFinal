package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class Versement implements Serializable {

    @ManyToOne
    private Client leClient;

    public Client getLeClient() {
        return leClient;
    }

    public void setLeClient(Client leClient) {
        this.leClient = leClient;
    }
    
    @ManyToOne
    private PorteFeuille lePortefeuille;

    public PorteFeuille getLePortefeuille() {
        return lePortefeuille;
    }

    public void setLePortefeuille(PorteFeuille lePortefeuille) {
        this.lePortefeuille = lePortefeuille;
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
        if (!(object instanceof Versement)) {
            return false;
        }
        Versement other = (Versement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Versement[ id=" + id + " ]";
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateVersement;

    /**
     * Get the value of dateVersement
     *
     * @return the value of dateVersement
     */
    public Date getDateVersement() {
        return dateVersement;
    }

    /**
     * Set the value of dateVersement
     *
     * @param dateVersement new value of dateVersement
     */
    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    private Double montant;

    /**
     * Get the value of montant
     *
     * @return the value of montant
     */
    public Double getMontant() {
        return montant;
    }

    /**
     * Set the value of montant
     *
     * @param montant new value of montant
     */
    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
