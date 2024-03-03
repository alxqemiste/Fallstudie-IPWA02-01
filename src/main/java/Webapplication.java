import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Named
@ApplicationScoped
public class Webapplication implements Serializable
{
    
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("webapplication");
    private static Webapplication instance = new Webapplication();
    private List<Ghostnet> netlist = new ArrayList<Ghostnet>();
  
    
    public List<Ghostnet> getNetlist() {
        EntityManager em = emf.createEntityManager();
        
        try { 
            Query q = em.createQuery("SELECT g from Ghostnet g");
            List<Ghostnet> net = q.getResultList();
            return net;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    
   

    public Webapplication(){
        /*     
        netlist.add(new Ghostnet(1,"word.word.word", 3, "gemeldet"));
        netlist.add(new Ghostnet(2, "coord.coord.coord", 4, "wird geborgen", "admin"));*/

    }

   
    public static Webapplication getWebapplication(){
        return instance;
    }

   public void soutPrintLn(){
       System.out.println("methode");
   }

   
    
   
}