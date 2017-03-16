package com.bourse.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@Inheritance
    (strategy=InheritanceType.JOINED)
public class PorteFeuille implements Serializable {

    @OneToMany(mappedBy = "lePorteFeuille")
    private List<Contenu> lesContenus;

    public List<Contenu> getLesContenus() {
        return lesContenus;
    }

    public void setLesContenus(List<Contenu> lesContenus) {
        this.lesContenus = lesContenus;
    } 
    
    @OneToMany(mappedBy = "lePortefeuille")
    private List<Versement> lesVersements;

    public List<Versement> getLesVersements() {
        return lesVersements;
    }

    public void setLesVersements(List<Versement> lesVersements) {
        this.lesVersements = lesVersements;
    }

    @OneToOne
    private Contrat leContrat;

    public Contrat getLeContrat() {
        return leContrat;
    }

    public void setLeContrat(Contrat leContrat) {
        this.leContrat = leContrat;
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
        if (!(object instanceof PorteFeuille)) {
            return false;
        }
        PorteFeuille other = (PorteFeuille) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.PorteFeuille[ id=" + id + " ]";
    }

    private Double liquidite;
    /**
     * Get the value of liquidite
     * @return the value of liquidite
     */
    public Double getLiquidite() {
        return liquidite;
    }
    /**
     * Set the value of liquidite
     * @param liquidite new value of liquidite
     */
    public void setLiquidite(Double liquidite) {
        this.liquidite = liquidite;
    }

    private Double montantInitial;

    /**
     * Get the value of montantInitial
     *
     * @return the value of montantInitial
     */
    public Double getMontantInitial() {
        return montantInitial;
    }

    /**
     * Set the value of montantInitial
     *
     * @param montantInitial new value of montantInitial
     */
    public void setMontantInitial(Double montantInitial) {
        this.montantInitial = montantInitial;
    }  
}
