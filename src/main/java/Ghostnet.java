import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Ghostnet implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String location;
    private int size;
    private String status = "reported";
    
    @ManyToOne
    private Operator responsibleOperator;
    
      
    @Transient
    private Ghostnet newNet = null;
    
    public Ghostnet(){}

    public Ghostnet(String location, int size) {
        this.location = location;
        this.size = size;
    }
    
    public Ghostnet(int id, String location, int size) {
        this(location, size);
        this.id = id;
    }

    public Ghostnet(int id, String location, int size, String status) {
        this(id, location, size);
        this.status = status;
    }
    
    public Ghostnet(int id, String location, int size, String status, Operator responsibleOperator) {
        this(id, location, size, status);
        this.responsibleOperator = responsibleOperator;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
 
    public Ghostnet getNewNet() {
        if(null == newNet) {
            this.newNet = new Ghostnet();
        }
         return this.newNet;
    }

    public void setNewNet(Ghostnet newNet) {
        this.newNet = newNet;
    }

    public Operator getResponsibleOperator() {
        return responsibleOperator;
    }

    public void setResponsibleOperator(Operator responsibleOperator) {
        this.responsibleOperator = responsibleOperator;
    }
    
  
    
    
    
}
