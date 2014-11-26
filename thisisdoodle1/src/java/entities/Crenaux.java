/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;


import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MB
 */
@Entity
@XmlRootElement
public class Crenaux implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "crenauxSequence")
    @SequenceGenerator(name = "crenauxSequence",sequenceName = "Seq_crenaux",initialValue = 1,allocationSize = 1)
    private Long id;
     private String debut;
      private String fin;

    /*
    @ManyToMany(mappedBy = "crenaux")
    private Collection<Evenement> events;
     */
    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
  


   

    
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
        if (!(object instanceof Crenaux)) {
            return false;
        }
        Crenaux other = (Crenaux) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Crenaux[ id=" + id + " ]";
    }

  
    
}
