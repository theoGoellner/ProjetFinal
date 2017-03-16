package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

@Entity
public class PERP extends PorteFeuille implements Serializable {

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
        if (!(object instanceof PERP)) {
            return false;
        }
        PERP other = (PERP) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.PERP[ id=" + id + " ]";
    }
 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateOuverture;
    /**
     * Get the value of dateOuverture
     * @return the value of dateOuverture
     */
    public Date getDateOuverture() {
        return dateOuverture;
    }
    /**
     * Set the value of dateOuverture
     * @param dateOuverture new value of dateOuverture
     */
    public void setDateOuverture(Date dateOuverture) {
        this.dateOuverture = dateOuverture;
    }
  
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFermeture;
    /**
     * Get the value of dateFermeture
     * @return the value of dateFermeture
     */
    public Date getDateFermeture() {
        return dateFermeture;
    }
    /**
     * Set the value of dateFermeture
     * @param dateFermeture new value of dateFermeture
     */
    public void setDateFermeture(Date dateFermeture) {
        this.dateFermeture = dateFermeture;
    }
}
