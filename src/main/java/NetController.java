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
    
    private int id;
    private String location;
    private int size;
    
    private int netId = 0;
    private Ghostnet newNet;
    

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }
    
    
    
    public void reportNet() {
        newNet = new Ghostnet(id, location, size);
        Webapplication.getInstance().saveNetToDB(newNet);
    }
    
    public void registerForRetrieval() {
        
        EntityManager em= emf.createEntityManager();;
        if (this.netId != 0) {
            
            try {
                newNet=em.find(Ghostnet.class, netId);
                Operator operator = em.find(Operator.class, LoginController.getOperator().getOperatorId());

                System.out.println("RO: " + newNet.getResponsibleOperator());

                if(newNet.getResponsibleOperator() == null) {
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
            }
            
        } else {
            System.out.println("Keine Net-ID gesetzt");
            //TODO Errorhandling
        }
        
        netId = 0;
        
    }

 
    
  
    
    
}
