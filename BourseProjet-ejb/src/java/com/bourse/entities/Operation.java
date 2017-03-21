package com.bourse.entities;

import com.bourse.enumeration.EnumEtatOperation;
import com.bourse.enumeration.EnumTypeOperation;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/** 
 * Définition de la classe Operation qui contient les attributs suivants : 
 */

@Entity
public class Operation implements Serializable {

    @OneToMany(mappedBy = "operation")
    private List<JournalActionSurOperation> journalActionSurOperations;

    public List<JournalActionSurOperation> getJournalActionSurOperations() {
        return journalActionSurOperations;
    }

    public void setJournalActionSurOperations(List<JournalActionSurOperation> journalActionSurOperations) {
        this.journalActionSurOperations = journalActionSurOperations;
    }

    @ManyToOne
    private PorteFeuille portefeuilleCible;

    public PorteFeuille getPortefeuilleCible() {
        return portefeuilleCible;
    }

    public void setPortefeuilleCible(PorteFeuille portefeuilleCible) {
        this.portefeuilleCible = portefeuilleCible;
    }

    @ManyToOne
    private PorteFeuille portefeuilleSource;

    public PorteFeuille getPortefeuilleSource() {
        return portefeuilleSource;
    }

    public void setPortefeuilleSource(PorteFeuille portefeuilleSource) {
        this.portefeuilleSource = portefeuilleSource;
    } 
    
    @OneToOne
    private Communication communication;

    public Communication getCommunication() {
        return communication;
    }

    public void setCommunication(Communication communication) {
        this.communication = communication;
    }
    
    @ManyToOne
    private Titre leTitre;

    public Titre getLeTitre() {
        return leTitre;
    }

    public void setLeTitre(Titre leTitre) {
        this.leTitre = leTitre;
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
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Operation[ id=" + id + " ]";
    }
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateOperation;
    /**
     * Get the value of dateOperation
     * @return the value of dateOperation
     */
    public Date getDateOperation() {
        return dateOperation;
    }
    /**
     * Set the value of dateOperation
     * @param dateOperation new value of dateOperation
     */
    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    private EnumTypeOperation TypeOperation;
    /**
     * Get the value of TypeOperation
     * @return the value of TypeOperation
     */
    public EnumTypeOperation getTypeOperation() {
        return TypeOperation;
    }
    /**
     * Set the value of TypeOperation
     * @param TypeOperation new value of TypeOperation
     */
    public void setTypeOperation(EnumTypeOperation TypeOperation) {
        this.TypeOperation = TypeOperation;
    }

    private EnumEtatOperation etat;

    /**
     * Get the value of etat
     *
     * @return the value of etat
     */
    public EnumEtatOperation getEtat() {
        return etat;
    }

    /**
     * Set the value of etat
     *
     * @param etat new value of etat
     */
    public void setEtat(EnumEtatOperation etat) {
        this.etat = etat;
    }

    
    private int quantite;

    /**
     * Get the value of quantite
     *
     * @return the value of quantite
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Set the value of quantite
     *
     * @param quantite new value of quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateLimite;

    /**
     * Get the value of dateLimite
     *
     * @return the value of dateLimite
     */
    public Date getDateLimite() {
        return dateLimite;
    }

    /**
     * Set the value of dateLimite
     *
     * @param dateLimite new value of dateLimite
     */
    public void setDateLimite(Date dateLimite) {
        this.dateLimite = dateLimite;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateExecution;

    /**
     * Get the value of dateExecution
     *
     * @return the value of dateExecution
     */
    public Date getDateExecution() {
        return dateExecution;
    }

    /**
     * Set the value of dateExecution
     *
     * @param dateExecution new value of dateExecution
     */
    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }
    
    private Double prix;

    /**
     * Get the value of prix
     *
     * @return the value of prix
     */
    public Double getPrix() {
        return prix;
    }

    /**
     * Set the value of prix
     *
     * @param prix new value of prix
     */
    public void setPrix(Double prix) {
        this.prix = prix;
    }
   
    private Boolean origineClient;

    /**
     * Get the value of origineClient
     *
     * @return the value of origineClient
     */
    public Boolean getOrigineClient() {
        return origineClient;
    }

    /**
     * Set the value of origineClient
     *
     * @param origineClient new value of origineClient
     */
    public void setOrigineClient(Boolean origineClient) {
        this.origineClient = origineClient;
    }
    
    private Boolean avisCourtier;

    /**
     * Get the value of avisCourtier
     *
     * @return the value of avisCourtier
     */
    public Boolean isAvisCourtier() {
        return avisCourtier;
    }

    /**
     * Set the value of avisCourtier
     *
     * @param avisCourtier new value of avisCourtier
     */
    public void setAvisCourtier(Boolean avisCourtier) {
        this.avisCourtier = avisCourtier;
    }

    
}
