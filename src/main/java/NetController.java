import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Named
@ViewScoped
public class NetController implements Serializable {
    
    private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("webapplication");
    
    private String location;
    private int size;
    private int netId;


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    
    public void reportNet() {
        EntityManager em = emf.createEntityManager();
        Ghostnet newNet = new Ghostnet(netId, location, size, "reported");
        
        try {
            Operator operator = em.find(Operator.class, LoginController.getOperator().getOperatorId());
            newNet.setResponsibleOperator(operator); //Hier gehts weiter
            
        } catch (Exception e){
            System.out.println(e);
            //TODO Errorhandling
            System.out.println("Kein Benutzer eingeloggt");

        }

        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(newNet);
        t.commit();
        em.close();
        
        location = "";
        size=0;
    }
    
    public void registerForRetrieval() {
        
        EntityManager em= emf.createEntityManager();;
   
        try {
            Ghostnet newNet=em.find(Ghostnet.class, netId);
            Operator operator = em.find(Operator.class, LoginController.getOperator().getOperatorId());

            if(!"Reported".equals(newNet.getStatus())) {   //newNet.getStatus != "Reported"
                newNet.setResponsibleOperator(operator);
                newNet.setStatus("Retrieval Pending");

                EntityTransaction t = em.getTransaction();
                t.begin();
                em.persist(newNet);
                t.commit();
                em.close();
            } else {
                System.out.println("Um dieses Netz kümmert sich schon jemand!");
                //TODO Errorhandling
            }
        }
        catch (Exception e){
            System.out.println(e);
            //TODO Errorhandling
            System.out.println("Netz nicht gefunden");

        }

        netId = 0;
        
    }
    
    public void markNetAsRetrieved () {
        
        EntityManager em= emf.createEntityManager();;

        try {
            Ghostnet newNet=em.find(Ghostnet.class, netId);
            Operator operator = em.find(Operator.class, LoginController.getOperator().getOperatorId());

            if(!"Retrieval Pending".equals(newNet.getStatus())) {

                System.out.println("Dieses Netz wurde noch nicht für eine aktive Bergung gemeldet");
                //TODO Errorhandling

            } else {
                newNet.setStatus("Retrieved");
                newNet.setResponsibleOperator(operator);
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.persist(newNet);
                t.commit();
                em.close();

            }
        }
        catch (Exception e){
            System.out.println("Dieses Netz exisitert nicht!");
            System.out.println(e);
            //TODO Errorhandling
        }
            

        
        netId = 0;

    }
    
    public void markNetAsMissing () {
        
        EntityManager em= emf.createEntityManager();

            
        try {
            Ghostnet newNet=em.find(Ghostnet.class, netId);
            Operator operator = em.find(Operator.class, LoginController.getOperator().getOperatorId());

            if(operator.getPhonenumber().equals("")) {

                System.out.println("Dieser Benutzer hat keine Telefonnummer angegeben und darf daher Netze nicht als vermisst melden");
                //TODO Errorhandling

            } else if(!"Retrieval Pending".equals(newNet.getStatus())) {

                System.out.println("Dieses Netz wurde noch nicht für eine aktive Bergung gemeldet");
                //TODO Errorhandling

            }else {
                newNet.setStatus("Missing");
                newNet.setResponsibleOperator(operator);
                EntityTransaction t = em.getTransaction();
                t.begin();
                em.persist(newNet);
                t.commit();
                em.close();

            }
        }
        catch (Exception e){
            System.out.println("Dieses Netz exisitert nicht!");
            System.out.println(e);
            //TODO Errorhandling
        }
            

        
        netId = 0;
        
    }
   
}
