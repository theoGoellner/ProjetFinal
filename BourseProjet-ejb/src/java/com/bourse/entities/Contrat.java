package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 * Définiidtion de la classe Contrat qui contient comme attributs : 
 *      dateDebut       : la date de debut de contrat
 *      dateFin         : la date de fin de contrat
 *      RIB             : RIB du compte bancaire sur lequel les opération sont effectuées
 *      typeContrat     :  
 */

@Entity
public class Contrat implements Serializable {

    @OneToOne(mappedBy = "leContrat")
    private PorteFeuille porteFeuille;

    public PorteFeuille getPorteFeuille() {
        return porteFeuille;
    }

    public void setPorteFeuille(PorteFeuille porteFeuille) {
        this.porteFeuille = porteFeuille;
    }

    @ManyToOne
    private Client leClient;

    public Client getLeClient() {
        return leClient;
    }

    public void setLeClient(Client leClient) {
        this.leClient = leClient;
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
        if (!(object instanceof Contrat)) {
            return false;
        }
        Contrat other = (Contrat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Contrat[ id=" + id + " ]";
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
    /**
     * Get the value of dateDebut
     * @return the value of dateDebut
     */
    public Date getDateDebut() {
        return dateDebut;
    }
    /**
     * Set the value of dateDebut
     * @param dateDebut new value of dateDebut
     */
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    /**
     * Get the value of dateFin
     * @return the value of dateFin
     */
    public Date getDateFin() {
        return dateFin;
    }
    /**
     * Set the value of dateFin
     * @param dateFin new value of dateFin
     */
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    private String RIB;
    /**
     * Get the value of RIB
     * @return the value of RIB
     */
    public String getRIB() {
        return RIB;
    }

    /**
     * Set the value of RIB
     *
     * @param RIB new value of RIB
     */
    public void setRIB(String RIB) {
        this.RIB = RIB;
    }

    private String typeContrat;
    /**
     * Get the value of typeContrat
     * @return the value of typeContrat
     */
    public String getTypeContrat() {
        return typeContrat;
    }
    /**
     * Set the value of typeContrat
     * @param typeContrat new value of typeContrat
     */
    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }   
}
