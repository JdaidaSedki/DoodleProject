/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author MB
 */
@Entity
@NamedQueries({
@NamedQuery(name = "connexion", query="select p from Participant p where p.pseudo = :login AND p.mdp = :mdp")})
@XmlRootElement
public class Participant implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
   //  @GeneratedValue(strategy = GenerationType.AUTO)
 @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "partSequence")
    @SequenceGenerator(name = "partSequence",sequenceName = "Seq_participant",initialValue = 1,allocationSize = 1)
    private Long id;
    private String nom;
    private String prenom;
    private String pseudo;
    private String adresse;
    private String mdp;
    private String email;
    
    // ajouter colonne de jointure au lieu d'un table, optimiser perf
    // utiliser pour détermination propriétaire
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "pt_fk")
    private Collection<Evenement> evenements;
    
 
     // ahouter table jointue pour déterminer memebres des événements
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "pt_evnt", 
    joinColumns = @JoinColumn(name = "pt_fk"), 
    inverseJoinColumns = @JoinColumn(name = "evnt_fk"))
    private Collection<Evenement> membres;
    
  @OneToMany(mappedBy = "part")
  Collection<Response> resps;

    
    
    @XmlTransient
    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }

    @XmlTransient
    public Collection<Evenement> getMembres() {
        return membres;
    }

    public void setMembres(Collection<Evenement> membres) {
        this.membres = membres;
    }
   

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
//    @JsonIgnore
    public Collection<Response> getResps() {
        return resps;
    }

    public void setResps(Collection<Response> resps) {
        this.resps = resps;
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
        if (!(object instanceof Participant)) {
            return false;
        }
        Participant other = (Participant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.doodle.entity.Participant[ id=" + id + " ]";
    }
    
}
