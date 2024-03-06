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

    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }
    
    
    
    public void reportNet() {
        if(LoginController.getOperator().getOperatorId() != 0 && size > 0) {
            newNet = new Ghostnet(netId, location, size, "reported");
            Webapplication.getInstance().saveNetToDB(newNet);
            location = "";
            size=0;
        } else {
            System.out.println("Du bist anonym!");
            //TODO Errohandling
        }

    }
    
    public void registerForRetrieval() {
        
        System.out.println(LoginController.getOperator().getOperatorId());
        
        EntityManager em= emf.createEntityManager();;
        if (LoginController.getOperator().getOperatorId() != 0) {
            
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
            System.out.println("Du bist anonym!");
            //TODO Errorhandling
        }
        
        netId = 0;
        
    }
    
    public void markNetAsRetrieved () {
        
        EntityManager em= emf.createEntityManager();;
        if (LoginController.getOperator().getOperatorId() != 0) {
            
            try {
                newNet=em.find(Ghostnet.class, netId);
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
            
        } else {
            System.out.println("Du bist anonym!");
            //TODO Errorhandling
        }
        
        netId = 0;

    }

    
    public void markNetAsMissing () {
        
        EntityManager em= emf.createEntityManager();;
        if (LoginController.getOperator().getOperatorId() != 0) {
            
            try {
                newNet=em.find(Ghostnet.class, netId);
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
            
        } else {
            System.out.println("Du bist anonym!");
            //TODO Errorhandling
        }
        
        netId = 0;
        
    }
 
    
  
    
    
}
