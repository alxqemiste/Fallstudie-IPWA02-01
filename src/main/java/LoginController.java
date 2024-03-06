import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {
    
    private String operatorName;
    private String password;
    
    private boolean anonymous;
    private boolean loggedIn = false;

    private static Operator operator = new Operator();
    
    
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

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String login(){
        
        operator.setOperatorName(operatorName);
        operator.setPassword(password);
        List<Operator> operatorList = Webapplication.getInstance().getOperatorList();
        for (Operator o : operatorList) {
            
                       
            if(o.equals(this.operator)) {
                if (o.getPhonenumber() == null) {
                    anonymous = true;
                } else {
                    anonymous = false;
                }
                loggedIn = true;
                operator.setOperatorId(o.getOperatorId());
                operator.setPhonenumber(o.getPhonenumber());

                return "index.xhtml";
            }
        }
        loggedIn = false;
        return "error.xhtml";
    }
    
    //TODO maybe delete later
    public void checkAttributes(){
        System.out.println(operator.getOperatorId() + " " + operator.getOperatorName() +" "+ operator.getPassword() + " " + operator.getPhonenumber());
    }
    
    
}
