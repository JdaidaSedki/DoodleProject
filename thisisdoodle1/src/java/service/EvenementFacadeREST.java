/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Evenement;
import entities.Participant;
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
@Path("entities.evenement")
public class EvenementFacadeREST extends AbstractFacade<Evenement> {
    @PersistenceContext(unitName = "thisisdoodle1PU")
    private EntityManager em;

    public EvenementFacadeREST() {
        super(Evenement.class);
    }

    /*
     * pour création de l'evenement et l'associer au participant qui l'a crée.
     * 
     * exp code : {"lieu":"sahloul","titre":"amphi","description":"test 5","dateEvnt":"61329999600000"}
     */
    @POST
    @Path("/{pseudo}")
    //@Override
    @Consumes({"application/xml", "application/json"})
    public void create(Evenement entity, @PathParam("pseudo") String pseudo) {
        super.create(entity);
        Query qer = em.createQuery(" Select e from Participant e where e.pseudo = :pseudo");
    //Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = :titre");
    qer.setParameter("pseudo", pseudo);
    Participant  p=(Participant)qer.getSingleResult();
    p.getEvenements().add(entity);
    
    }

    @PUT
    @Override
    @Consumes({"application/xml", "application/json"})
    public void edit(Evenement entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

  @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Evenement find(@PathParam("id") Long id) {
        return super.find(id);
    }

 /*  @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Evenement> findAll() {
        return super.findAll();
    }
*/
   /* @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Evenement> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }
*/
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
    
    /*
     * charger liste des évenements d'un participant ==> à utiliser pour charger les evenements 
     *      * qu'un participant possède !
     */
    
  @GET
    @Path("{pseudo}")
    @Produces({"application/xml", "application/json"})
     public List<Evenement> owner (@PathParam("pseudo") String pseudo){
             Query query=em.createQuery("select ev from Participant p join p.evenements ev where p.pseudo = :pseudo");
        query.setParameter("pseudo", pseudo);
        List <Evenement> evts=query.getResultList();
        return evts;
     }
  
    @GET
    @Path("/membre/{pseudo}")
    @Produces({"application/xml", "application/json"})
     public List<Evenement> participer (@PathParam("pseudo") String pseudo){
             Query query=em.createQuery("select ev from Participant p join p.membres ev where p.pseudo = :pseudo");
             
        query.setParameter("pseudo", pseudo);
        List <Evenement> evts=query.getResultList();
        return evts;
     }
   
  /*
   * pour ajouter un participant à l'evenement créer via pseduo du participant à inviter
   * si le participant existe msg => participant.pseudo added
   * sinon msg => utilisateur non trouvé 
   */
  @POST
  @Path("inviter/{pseudo}/{titre}")
  @Consumes({"application/xml", "application/json"})
public String inviter(@PathParam("pseudo") String pseudo,@PathParam("titre") String titre){
     
      String warning=" ";
      
      Query qer = em.createQuery(" Select e from Participant e where e.pseudo = :pseudo");
    Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = :titre");
    qer.setParameter("pseudo", pseudo);
    qe.setParameter("titre", titre);
   try{
  Participant  p=(Participant)qer.getSingleResult();
  Evenement e=(Evenement)qe.getSingleResult();
        p.getMembres().add(e);
   
   
   }catch(Exception e){
       warning="utilisateur non trouvé ";
  };
 if (warning.equals(" ")){warning = pseudo+" added.";}
        return "{ \"status\":\""+warning+"\"}";
    
  }    

  
  }
