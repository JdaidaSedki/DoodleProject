/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mb
 */
@Entity
@XmlRootElement
public class Response implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int choix;
    
    @ManyToOne
    private Evenement evnt;
    
    @ManyToOne
    private Participant part;
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getChoix() {
        return choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }

    public Evenement getEvnt() {
        return evnt;
    }

    public void setEvnt(Evenement evnt) {
        this.evnt = evnt;
    }

    public Participant getPart() {
        return part;
    }

    public void setPart(Participant part) {
        this.part = part;
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
        if (!(object instanceof Response)) {
            return false;
        }
        Response other = (Response) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Response[ id=" + id + " ]";
    }
    
}
