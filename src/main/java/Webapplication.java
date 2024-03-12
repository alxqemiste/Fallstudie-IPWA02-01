import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

@Named
@ApplicationScoped
public class Webapplication implements Serializable
{
    
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("webapplication");
    private static Webapplication instance = new Webapplication();
       
    public Webapplication(){
        
    }

    public List<Ghostnet> getNetlist() {
        EntityManager em = emf.createEntityManager();
        
        try { 
            Query q = em.createQuery("SELECT g from Ghostnet g WHERE g.status != 'Retrieved' ");
            List<Ghostnet> net = q.getResultList();
            return net;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
    public List<Operator> getOperatorList() {

        EntityManager em = emf.createEntityManager();
        
        try { 
            Query q = em.createQuery("SELECT o from Operator o");
            List<Operator> operator = q.getResultList();
            return operator;
        } catch (Exception e) {
            System.out.println(e);
            //TODO Errorhandling if necessary
            return null;
        }
    }

    public static Webapplication getInstance(){
        return instance;
    }

    public void saveOperatorToDB(Operator operator) {
        EntityManager em = emf.createEntityManager();
        
        EntityTransaction t = em.getTransaction();
        
        t.begin();
        em.persist(operator);
        t.commit();
        
        em.close();
        
        
    }

}