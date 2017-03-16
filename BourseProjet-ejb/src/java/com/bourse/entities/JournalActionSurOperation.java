package com.bourse.entities;

import com.bourse.enumeration.EnumEtatOperation;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

@Entity
public class JournalActionSurOperation implements Serializable {

    @ManyToOne
    private Employe unEmploye;

    public Employe getUnEmploye() {
        return unEmploye;
    }

    public void setUnEmploye(Employe unEmploye) {
        this.unEmploye = unEmploye;
    }
    
    @ManyToOne
    private Operation operation;

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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
        if (!(object instanceof JournalActionSurOperation)) {
            return false;
        }
        JournalActionSurOperation other = (JournalActionSurOperation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.JournalActionSurOperation[ id=" + id + " ]";
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateAction;

    /**
     * Get the value of dateAction
     *
     * @return the value of dateAction
     */
    public Date getDateAction() {
        return dateAction;
    }

    /**
     * Set the value of dateAction
     *
     * @param dateAction new value of dateAction
     */
    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    private EnumEtatOperation etatOperation;

    /**
     * Get the value of etatOperation
     *
     * @return the value of etatOperation
     */
    public EnumEtatOperation getEtatOperation() {
        return etatOperation;
    }

    /**
     * Set the value of etatOperation
     *
     * @param etatOperation new value of etatOperation
     */
    public void setEtatOperation(EnumEtatOperation etatOperation) {
        this.etatOperation = etatOperation;
    }
}
