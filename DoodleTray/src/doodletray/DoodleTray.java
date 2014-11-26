/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package doodletray;
import entity.Crenaux;
import entity.Evenement;
import entity.Participant;
import entity.Response;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author MB
 */
public class DoodleTray {

    /**
     * @param args the command line arguments
     
     */ 
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DoodleTrayPU");
  static EntityManager em = emf.createEntityManager();
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
        // TODO code application logic here
         em.getTransaction().begin();
         
        // TODO code application logic here
  Participant p=new Participant();
    Participant p1=new Participant();
    Participant p2=new Participant();
    Crenaux c=new Crenaux();
    Crenaux c1=new Crenaux();
    Crenaux c2=new Crenaux();
    Crenaux c3=new Crenaux();
     Evenement e=new Evenement();
     Evenement e1=new Evenement();
     
     
    
  //  Evenement e=new Evenement();
    
 //  e=em.find(Evenement.class, 1L);
    
    
      p.setAdresse("souuse");
         p.setEmail("test22@mail");
         p.setMdp("143");
         p.setNom("s");
         p.setPrenom("j");
         p.setPseudo("efe");
   
         
           p1.setAdresse("sousse");
         p1.setEmail("test52@mail");
         p1.setMdp("963");
         p1.setNom("so");
         p1.setPrenom("je");
         p1.setPseudo("abd");

     
       
           p2.setAdresse("tunis");
         p2.setEmail("tloo2@mail");
         p2.setMdp("789");
         p2.setNom("mb");
         p2.setPrenom("bm");
         p2.setPseudo("choker");
        
          
       
         e.setLieu("tunis1");
         e.setTitre("test1");
         e.setDescription("my test case");
         e.setDateEvnt("12/09/2013");
         
    
               
         e1.setLieu("souuse");
         e1.setTitre("test2");
         e1.setDescription("home test");
         e1.setDateEvnt("20/09/2013");
      
       
         c.setDebut("12:30");
         c.setFin("14:30");
         
         c1.setDebut("15:30");
         c1.setFin("16:30");
              
         c2.setDebut("10:30");
         c2.setFin("11:30");
         
         c3.setDebut("18:30");
         c3.setFin("20:00");
        
         
     
       //  em.persist(c);
        em.persist(p);
           em.persist(p1);
           em.persist(p2);
           em.persist(e);
           em.persist(e1);
           em.persist(c);
           em.persist(c1);
           em.persist(c2);
           em.persist(c3);
           
           em.flush();  
     
       Response r=new Response();
       r.setChoix(1);
       r.setEvnt(e);
       r.setPart(p2);
       em.persist(r);
         p.getEvenements().add(e);
         p.getMembres().add(e);
         p2.getEvenements().add(e1);
         p1.getMembres().add(e);
         p2.getMembres().add(e);
        e.getCrenaux().add(c);
        e.getCrenaux().add(c1);
        e.getCrenaux().add(c2);
        e1.getCrenaux().add(c2);
        e1.getCrenaux().add(c3);
        e1.getCrenaux().add(c);
        
 
      
      
      /*   Query query= em.createNamedQuery("connexion");
         query.setParameter("login", "test");
         query.setParameter("mdp","125");
         Participant p=(Participant)query.getSingleResult();
         System.out.println(p.getId()+" "+p.getNom());inner join par.membres evnt
     */
          // Query query=em.createQuery("select e from Evenement e where evnt.id =  (Select p.id from Participant p inner join p.evenements evnt.id  )");
        //  Query query =em.createQuery("select ev from Participant p join p.evenements ev " +
//"where p.pseudo = :pseudo  ");
  //      query.setParameter("pseudo", "sedki");
   // List<Evenement> pt= query.getResultList();
 /*   Participant p =new Participant();
    Evenement e=new Evenement();
    Query qer = em.createQuery(" Select e from Participant e where e.pseudo = 'abd'");
    Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = 'evenets' ");
    p=(Participant)qer.getSingleResult();
    
   e=(Evenement)qe.getSingleResult();
    p.getMembres().add(e);
    

       System.out.println(p.getAdresse()+" "+p.getNom());
       */
      /*   String status=" ";
       Query query= em.createQuery("select p from Participant p where p.pseudo = :pseudo AND p.mdp = :mdp" );
     query.setParameter("pseudo", "abd");
     query.setParameter("mdp", "89");
    
     
     try{
     Participant p=(Participant)query.getSingleResult();
     }
     catch(Exception e){
    status= "echec";
         System.out.println(status);
         
     };
    
           if( !status.equals("echec")) 
           {status="success";
               System.out.println(status);}
     
 */
              //   p.getMembres().add(e);
               //  p1.getMembres().add(e);
         /*        Participant p = new Participant();
                 p.setAdresse("test");
         p.setEmail("nomail@plz");
         p.setMdp("azert");
         p.setNom("iop");
         p.setPrenom("jpp");
         p.setPseudo("choker");
         em.persist(p);*/
  /*       
           String warning=" ";
      
      Query qer = em.createQuery(" Select ev from Evenement e where e.pseudo ");
    Query qe=em.createQuery(" Select ev from Evenement ev where ev.titre = 'test1'");
    try{
  Participant  p=(Participant)qer.getSingleResult();
   Evenement e=(Evenement)qe.getSingleResult();
    p.getMembres().add(e);
    }catch(Exception e){
        warning="utilisateur non trouvé ";
    };
 if (warning.equals(" ")){warning = " added.";}
        System.out.println(warning);
    */
  
     em.getTransaction().commit();
    em.close();
    emf.close();
    }
}