package com.bourse.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * Définition de la Classe Titre qui contient comme attributs
 *      id                  : id du titre
 *      nom                 : nom du titre 
 *      emetteur            : société emettrice du titre
 *      devise              : devise dans laquelle le titre est coté
 *      montantNominal      : valeur du titre lors de son emission
 *      dateEmission        : date à laquelle le titre a été émis
 *      codeIsin            : code ISIN d'identification du titre
 *      codeSicovam         : code SICOVAM d'identification du titre
 */

@Entity
@Inheritance
    (strategy=InheritanceType.JOINED)
public class Titre implements Serializable {

    @OneToMany(mappedBy = "titre")
    private List<Contenu> lesContenus;

    public List<Contenu> getLesContenus() {
        return lesContenus;
    }

    public void setLesContenus(List<Contenu> lesContenus) {
        this.lesContenus = lesContenus;
    }

    @OneToMany(mappedBy="leTitre")
    private List<Courtage> lesCourtages;

    public List<Courtage> getLesCourtages() {
        return lesCourtages;
    }

    public void setLesCourtages(List<Courtage> lesCourtages) {
        this.lesCourtages = lesCourtages;
    }
        
    @ManyToOne
    private Marche leMarche;

    public Marche getLeMarche() {
        return leMarche;
    }

    public void setLeMarche(Marche leMarche) {
        this.leMarche = leMarche;
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
        if (!(object instanceof Titre)) {
            return false;
        }
        Titre other = (Titre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Titre[ id=" + id + " ]";
    }
    
    private String nom;

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    private String emetteur;

    /**
     * Get the value of emetteur
     *
     * @return the value of emetteur
     */
    public String getEmetteur() {
        return emetteur;
    }

    /**
     * Set the value of emetteur
     *
     * @param emetteur new value of emetteur
     */
    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    private String devise;

    /**
     * Get the value of devise
     *
     * @return the value of devise
     */
    public String getDevise() {
        return devise;
    }

    /**
     * Set the value of devise
     *
     * @param devise new value of devise
     */
    public void setDevise(String devise) {
        this.devise = devise;
    }

    private Double montantNominal;

    /**
     * Get the value of montantNominal
     *
     * @return the value of montantNominal
     */
    public Double getMontantNominal() {
        return montantNominal;
    }

    /**
     * Set the value of montantNominal
     *
     * @param montantNominal new value of montantNominal
     */
    public void setMontantNominal(Double montantNominal) {
        this.montantNominal = montantNominal;
    }

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmission;

    /**
     * Get the value of dateEmission
     *
     * @return the value of dateEmission
     */
    public Date getDateEmission() {
        return dateEmission;
    }

    /**
     * Set the value of dateEmission
     *
     * @param dateEmission new value of dateEmission
     */
    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    private String codeISIN;

    /**
     * Get the value of codeISIN
     *
     * @return the value of codeISIN
     */
    public String getCodeISIN() {
        return codeISIN;
    }

    /**
     * Set the value of codeISIN
     *
     * @param codeISIN new value of codeISIN
     */
    public void setCodeISIN(String codeISIN) {
        this.codeISIN = codeISIN;
    }

    private String codeSICOVAM;

    /**
     * Get the value of codeSICOVAM
     *
     * @return the value of codeSICOVAM
     */
    public String getCodeSICOVAM() {
        return codeSICOVAM;
    }

    /**
     * Set the value of codeSICOVAM
     *
     * @param codeSICOVAM new value of codeSICOVAM
     */
    public void setCodeSICOVAM(String codeSICOVAM) {
        this.codeSICOVAM = codeSICOVAM;
    }
}
