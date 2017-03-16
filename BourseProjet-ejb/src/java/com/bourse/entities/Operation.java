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


    @ManyToOne
    private Contenu leContenuSource;

    public Contenu getLeContenuSource() {
        return leContenuSource;
    }

    public void setLeContenuSource(Contenu leContenuSource) {
        this.leContenuSource = leContenuSource;
    }

    public Contenu getLeContenuCible() {
        return leContenuCible;
    }

    public void setLeContenuCible(Contenu leContenuCible) {
        this.leContenuCible = leContenuCible;
    }

    
    @ManyToOne
    private Contenu leContenuCible;

 
    @OneToMany(mappedBy = "operation")
    private List<JournalActionSurOperation> lesJournalActionSurOperation;

    public List<JournalActionSurOperation> getLesJournalActionSurOperation() {
        return lesJournalActionSurOperation;
    }

    public void setLesJournalActionSurOperation(List<JournalActionSurOperation> lesJournalActionSurOperation) {
        this.lesJournalActionSurOperation = lesJournalActionSurOperation;
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
    private Contenu contenu;

    public Contenu getContenu() {
        return contenu;
    }

    public void setContenu(Contenu contenu) {
        this.contenu = contenu;
    }
    
    @ManyToOne
    private Employe employe;

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
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
    
    private Double prixAchat;

    /**
     * Get the value of prixAchat
     *
     * @return the value of prixAchat
     */
    public Double getPrixAchat() {
        return prixAchat;
    }

    /**
     * Set the value of prixAchat
     *
     * @param prixAchat new value of prixAchat
     */
    public void setPrixAchat(Double prixAchat) {
        this.prixAchat = prixAchat;
    }

    private Double prixVente;

    /**
     * Get the value of prixVente
     *
     * @return the value of prixVente
     */
    public Double getPrixVente() {
        return prixVente;
    }

    /**
     * Set the value of prixVente
     *
     * @param prixVente new value of prixVente
     */
    public void setPrixVente(Double prixVente) {
        this.prixVente = prixVente;
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
