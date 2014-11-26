/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Participant;
import java.net.URI;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author MB
 */
@Stateless
@Path("entities.participant")
public class ParticipantFacadeREST extends AbstractFacade<Participant> {
    @PersistenceContext(unitName = "thisisdoodle1PU")
    private EntityManager em;
  private UriInfo uriInfo;
    public ParticipantFacadeREST() {
        super(Participant.class);
    }
/*
 *  curl -X POST --data-binary "{ \"nom\":\"maro\", \"prenom\":\"bro\", 
\"pseudo\":\"mb5\",\"adresse\":\"mourouj\",\"mdp\":\"555\",
\"email\":\"mail@mail.org\" }" -H "Content-Type: application/json" http://localhost:8080/loool/webresources/entities.participant -v
-H "Content-Type: application/json" http://localhost:8080/loool/webresources/entities.evenement/inviter/choker/test1
 */
    @POST
    @Override
    @Consumes({"application/json", "application/xml"})
    public void create(Participant entity) {
        super.create(entity);
    }

    @PUT
    @Override
    @Consumes({"application/json", "application/xml"})
    public void edit(Participant entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Participant find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json", "application/xml"})
    public List<Participant> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json", "application/xml"})
    public List<Participant> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @GET
    @Path("/connexion/{pseudo}/{mdp}")
    @Produces({"application/json", "application/xml"})
    public String connexion(@PathParam("pseudo") String pseudo, @PathParam("mdp") String mdp){
      String status=" ";
       Query query= em.createQuery("select p from Participant p where p.pseudo = :pseudo AND p.mdp = :mdp" );
     query.setParameter("pseudo",pseudo);
     query.setParameter("mdp",mdp);
    
     
     try{
     Participant p=(Participant)query.getSingleResult();
     }
     catch(Exception e){
    status= "echec";
       
         
     };
    
           if(status.equals(" ")) {status= "success";}
     return  "{ \"status\":\""+status+"\"}";
    }
     
  
    
     @GET
     @Path("/participants/{titre}")
      @Produces("application/json,application/xml")
     public List<Participant> event(@PathParam("titre") String titre){
        Query query=em.createQuery("select par from Participant par " +"inner join par.membres evnt " + "where evnt.titre = :titre");
        query.setParameter("titre", titre);
        List <Participant> pts=query.getResultList();
        return pts;
    }
  
        
    }
    

