/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author MB
 */
@Entity
@XmlRootElement
public class Evenement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "evntSequence")
    @SequenceGenerator(name = "evntSequence",sequenceName = "Seq_evenement",initialValue = 1,allocationSize = 1)
    private Long id;
    private String lieu;
    private String titre;
     private String description;


     private String dateEvnt;

 
     
     @ManyToMany(mappedBy = "membres")
     private Collection<Participant> participants;
     @OneToMany(cascade ={CascadeType.PERSIST, CascadeType.REMOVE} )
     @JoinColumn(name  ="evnt_fk")
     private Collection<Crenaux> crenaux;
     
     @OneToMany(mappedBy = "evnt")
     Collection<Response> responses;
     
   /*  
     @ManyToMany
    @JoinTable(name = "evnt_date", 
    joinColumns = @JoinColumn(name = "evnt_fk"), 
    inverseJoinColumns = @JoinColumn(name = "crenaux_fk"))
    private Collection<Crenaux> crenaux;

  */
     
     
     
     
   
    @XmlTransient
    public Collection<Crenaux> getCrenaux() {
        return crenaux;
    }

    public void setCrenaux(Collection<Crenaux> crenaux) {
        this.crenaux = crenaux;
    }

    @XmlTransient
    public Collection<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<Participant> participants) {
        this.participants = participants;
    }
     

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
      public String getDateEvnt() {
        return dateEvnt;
    }

    public void setDateEvnt(String dateEvnt) {
        this.dateEvnt = dateEvnt;
    }

    @XmlTransient

    public Collection<Response> getResponses() {
        return responses;
    }

    public void setResponses(Collection<Response> responses) {
        this.responses = responses;
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
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.doodle.entity.Evenement[ id=" + id + " ]";
    }

  
    
}
