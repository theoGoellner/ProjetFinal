package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Définition de la Classe DateCourtage qui contient comme attributs
 *      date       : date du courtage
 */

@Entity
public class DateCourtage implements Serializable {
    
    @OneToMany (mappedBy="dateCourtage")
    private List<Courtage> lesCourtages;

    public List<Courtage> getLesCourtages() {
        return lesCourtages;
    }

    public void setLesCourtages(List<Courtage> lesCourtages) {
        this.lesCourtages = lesCourtages;
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
        if (!(object instanceof DateCourtage)) {
            return false;
        }
        DateCourtage other = (DateCourtage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.DateCourtage[ id=" + id + " ]";
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateCourtage;

    /**
     * Get the value of dateCourtage
     *
     * @return the value of dateCourtage
     */
    public Date getDateCourtage() {
        return dateCourtage;
    }

    /**
     * Set the value of dateCourtage
     *
     * @param dateCourtage new value of dateCourtage
     */
    public void setDdateCourtage(Date dateCourtage) {
        this.dateCourtage = dateCourtage;
    }

}
