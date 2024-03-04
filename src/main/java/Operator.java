import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(nullable = false)
    private String username = "admin";
    
    @Column(nullable = false)
    private String password = "admin";
    private String phonenumber;
      
    public Operator(){};

    public Operator(String username, String password) {
        this.username = username;
        this.password = password;

    }
    
    public Operator(String username, String password, String phonenumber) {
        this(username, password);
        this.phonenumber = phonenumber;

    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

     
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Operator) {
            Operator u = (Operator) obj;
            if(u.getUsername().equals(this.username) &&
               u.getPassword().equals(this.password))
                return true;
        }
        
        return false;
    }
    
    
}
