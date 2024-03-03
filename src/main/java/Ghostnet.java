import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ghostnet implements Serializable {
    
    @Id
    private int id;
    private String location;
    private int size;
    private String status;
    private String responsibleUser;
    private String phonenumber;
    
    public Ghostnet(){}

    public Ghostnet(int id, String location, int size, String status) {
        this.id = id;
        this.location = location;
        this.size = size;
        this.status = status;
    }
    
    public Ghostnet(int id, String location, int size, String status, String responsibleUser) {
        this(id, location, size, status);
        this.responsibleUser = responsibleUser;
    }
    
     public Ghostnet(int id, String location, int size, String status, String responsibleUser, String phonenumber) {
        this(id, location, size, status, responsibleUser);
        this.phonenumber = phonenumber;
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

    public String getResponsibleUser() {
        return responsibleUser;
    }

    public void setResponsibleUser(String responsibleUser) {
        this.responsibleUser = responsibleUser;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    
    
    
    
    
}
