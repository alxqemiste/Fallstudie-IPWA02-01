import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Operator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int operatorId;
    
    private String operatorName;
    private String password;
    private String phonenumber;

    public Operator(){};

    public Operator(String operatorName, String password) {
        this.operatorName = operatorName;
        this.password = password;

    }
    
    public Operator(String operatorName, String password, String phonenumber) {
        this(operatorName, password);
        this.phonenumber = phonenumber;

    }
    
    public Operator(int operatorId, String operatorName, String password, String phonenumber) {
        this(operatorName, password, phonenumber);
        this.operatorId = operatorId;

    }

    
    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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
            if(u.getOperatorName().equals(this.operatorName) &&
               u.getPassword().equals(this.password))
                return true;
        }
        
        return false;
    }
    
}
