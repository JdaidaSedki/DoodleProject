/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Evenement;
import entities.Participant;
import entities.Response;
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
@Path("entities.response")
public class ResponseFacadeREST extends AbstractFacade<Response> {
    @PersistenceContext(unitName = "thisisdoodle1PU")
    private EntityManager em;

    public ResponseFacadeREST() {
        super(Response.class);
    }

    @POST
    @Path("/{titre}/{pseudo}")
      @Consumes({"application/xml", "application/json"})
    public void create(Response entity,@PathParam("pseudo") String pseudo,@PathParam("titre") String titre ) {
        super.create(entity);
        Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = :titre");
          Query qer = em.createQuery(" Select e from Participant e where e.pseudo = :pseudo");
          qe.setParameter("titre", titre);
          qer.setParameter("pseudo",pseudo);
          Participant  p=(Participant)qer.getSingleResult();
          Evenement e=(Evenement)qe.getSingleResult();
          entity.setEvnt(e);
          entity.setPart(p);
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Response entity) {
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
    public Response find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Response> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Response> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
*/
    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
  /*  @GET
    @Path("/{titre}/{pseudo}")
    @Produces({"application/xml", "application/json"})
    public Response charger(@PathParam("titre") String titre,@PathParam("pseudo") String pseudo){
          Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = :titre");
          Query qer = em.createQuery(" Select e from Participant e where e.pseudo = :pseudo");
          qe.setParameter("titre", titre);
          qer.setParameter("pseudo",pseudo);
          Participant  p=(Participant)qer.getSingleResult();
          Evenement e=(Evenement)qe.getSingleResult();
          
          
    }*/

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
