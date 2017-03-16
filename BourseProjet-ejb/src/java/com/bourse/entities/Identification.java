package com.bourse.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

/**
 * Définition de la classe Identification qui sera utilisé dans l'Authentification et aura comme attributs : 
 *      id          : identifiant de la Classe 
 *      login       : identifiant de connexion à l'espace personnel
 *      pwd         : mots de passe de connexion à l'espace personnel
 *      TypeUser    : Determine si l'utilisateur est un client ou un employé
 *      IdUser      : l'identifiant du client ou de l'employé
 */

@Entity
public class Identification implements Serializable {

    @OneToMany(mappedBy = "identification")
    private List<JournalConnexion> lesConnexions;

    public List<JournalConnexion> getLesConnexions() {
        return lesConnexions;
    }

    public void setLesConnexions(List<JournalConnexion> lesConnexions) {
        this.lesConnexions = lesConnexions;
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
        if (!(object instanceof Identification)) {
            return false;
        }
        Identification other = (Identification) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bourse.entities.Identification[ id=" + id + " ]";
    }
    
    private String typeUser;
    /**
     * Get the value of typeUser
     * @return the value of typeUser
     */
    public String getTypeUser() {
        return typeUser;
    }
    /**
     * Set the value of typeUser
     * @param typeUser new value of typeUser
     */
    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    private Long idUser;
    /**
     * Get the value of idUser
     * @return the value of idUser
     */
    public Long getIdUser() {
        return idUser;
    }
    /**
     * Set the value of idUser
     * @param idUser new value of idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    @Column(nullable = false, unique = true)
    private String login;
    /**
     * Get the value of login
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }
    /**
     * Set the value of login
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    @Column(nullable = false)
    private String pwd;
    /**
     * Get the value of pwd
     * @return the value of pwd
     */
    public String getPwd() {
        return pwd;
    }
    /**
     * Set the value of pwd
     * @param pwd new value of pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }  
    
    private Boolean estActif;

    /**
     * Get the value of estActif
     *
     * @return the value of estActif
     */
    public Boolean isEstActif() {
        return estActif;
    }

    /**
     * Set the value of estActif
     *
     * @param estActif new value of estActif
     */
    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    private Boolean estBloque;

    /**
     * Get the value of estBloque
     *
     * @return the value of estBloque
     */
    public Boolean isEstBloque() {
        return estBloque;
    }

    /**
     * Set the value of estBloque
     *
     * @param estBloque new value of estBloque
     */
    public void setEstBloque(Boolean estBloque) {
        this.estBloque = estBloque;
    }

    @PrePersist
    public void initIdentification(){
        this.estBloque=false;
        this.estActif=true;
    }
}
