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
public class JournalConnexion implements Serializable {

    @ManyToOne
    private Identification identification;

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
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
        if (!(object instanceof JournalConnexion)) {
            return false;
        }
        JournalConnexion other = (JournalConnexion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.JournalConnexion[ id=" + id + " ]";
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateConnexion;

    /**
     * Get the value of dateConnexion
     *
     * @return the value of dateConnexion
     */
    public Date getDateConnexion() {
        return dateConnexion;
    }

    /**
     * Set the value of dateConnexion
     *
     * @param dateConnexion new value of dateConnexion
     */
    public void setDateConnexion(Date dateConnexion) {
        this.dateConnexion = dateConnexion;
    }
}
