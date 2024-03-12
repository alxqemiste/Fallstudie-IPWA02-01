import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    
    private String operatorName;
    private String password;
    private boolean loggedIn = false;
    
    private static Operator operator = new Operator(0, null, null, null);

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

    public static Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn=loggedIn;
    }

    
    public String login(){
                
        operator.setOperatorName(operatorName);
        operator.setPassword(password);
         
        List<Operator> operatorList = Webapplication.getInstance().getOperatorList();
        for (Operator o : operatorList) {
            
                       
            if(o.equals(this.operator)) {
                loggedIn = true;
                operator.setOperatorId(o.getOperatorId());
                operator.setPhonenumber(o.getPhonenumber());
                return "index.xhtml";
            }
        }
        loggedIn = false;
        //TODO Errorhandling
        return "error.xhtml";
    }
    
    public String logout() {

        operator = new Operator(0, null, null, null);
        this.operatorName = null;
        this.password = null;
        loggedIn = false;
        return "index?faces-redirect=true";
    }
    
}
