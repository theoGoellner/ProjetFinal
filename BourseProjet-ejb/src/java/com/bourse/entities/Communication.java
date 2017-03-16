package com.bourse.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Communication implements Serializable {

    @OneToOne(mappedBy = "communication")
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
        if (!(object instanceof Communication)) {
            return false;
        }
        Communication other = (Communication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Communication[ id=" + id + " ]";
    }
    
    private String referenceEnregistrementTph;

    /**
     * Get the value of referenceEnregistrementTph
     *
     * @return the value of referenceEnregistrementTph
     */
    public String getReferenceEnregistrementTph() {
        return referenceEnregistrementTph;
    }

    /**
     * Set the value of referenceEnregistrementTph
     *
     * @param referenceEnregistrementTph new value of referenceEnregistrementTph
     */
    public void setReferenceEnregistrementTph(String referenceEnregistrementTph) {
        this.referenceEnregistrementTph = referenceEnregistrementTph;
    }

}
