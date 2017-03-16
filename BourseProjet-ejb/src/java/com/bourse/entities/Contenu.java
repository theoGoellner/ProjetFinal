package com.bourse.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Contenu implements Serializable {

    @OneToMany(mappedBy = "leContenuSource")
    private List<Operation> lesOperationsVentes;

    public List<Operation> getLesOperationsVentes() {
        return lesOperationsVentes;
    }

    public void setLesOperationsVentes(List<Operation> lesOperationsVentes) {
        this.lesOperationsVentes = lesOperationsVentes;
    }


    @OneToMany(mappedBy = "leContenuCible")
    private List<Operation> lesOperationsAchats;

    public List<Operation> getLesOperationsAchats() {
        return lesOperationsAchats;
    }

    public void setLesOperationsAchats(List<Operation> lesOperationsAchats) {
        this.lesOperationsAchats = lesOperationsAchats;
    }
    
    @ManyToOne
    private PorteFeuille lePorteFeuille;

    public PorteFeuille getLePorteFeuille() {
        return lePorteFeuille;
    }

    public void setLePorteFeuille(PorteFeuille lePorteFeuille) {
        this.lePorteFeuille = lePorteFeuille;
    }

    @ManyToOne
    private Titre titre;

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
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
        if (!(object instanceof Contenu)) {
            return false;
        }
        Contenu other = (Contenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Contenu[ id=" + id + " ]";
    }

    private int quantiteDisponible;

    /**
     * Get the value of quantiteDisponible
     * @return the value of quantiteDisponible
     */
    public int getQuantiteDisponible() {
        return quantiteDisponible;
    }

    /**
     * Set the value of quantiteDisponible
     * @param quantiteDisponible new value of quantiteDisponible
     */
    public void setQuantiteDisponible(int quantiteDisponible) {
        this.quantiteDisponible = quantiteDisponible;
    }

}
