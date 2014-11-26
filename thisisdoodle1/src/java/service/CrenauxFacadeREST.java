/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Crenaux;
import entities.Evenement;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author mb
 */
@Stateless
@Path("entities.crenaux")
public class CrenauxFacadeREST extends AbstractFacade<Crenaux> {
    @PersistenceContext(unitName = "thisisdoodle1PU")
    private EntityManager em;

    public CrenauxFacadeREST() {
        super(Crenaux.class);
    }

    @POST
    @Path("/creer/{titre}")
  
    @Consumes({"application/xml", "application/json"})
    public void create(List<Crenaux> entity,@PathParam("titre") String titre) {
     Query query= em.createQuery("select e from Evenement e where e.titre = :titre");  
     query.setParameter("titre", titre);
     Evenement e=(Evenement)query.getSingleResult();
     
        
        for (int i=0;i< entity.size();i++){
           create(entity.get(i));
           e.getCrenaux().add(entity.get(i));
        
        }
        
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Crenaux entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }
  /* 
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Crenaux find(@PathParam("id") Long id) {
        return super.find(id);
    }

 @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Crenaux> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Crenaux> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
*/
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
 
    }
@GET
@Path("/{titre}")
@Produces({"application/xml", "application/json"})
public List<Crenaux> choix(@PathParam("titre") String titre){
     Query query= em.createQuery("select c from Evenement e join e.crenaux c where e.titre = :titre");
    query.setParameter("titre", titre);
     List<Crenaux> c=query.getResultList();
     return c;
     
}
@Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
