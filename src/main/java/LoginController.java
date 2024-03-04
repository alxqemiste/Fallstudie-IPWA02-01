import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private String username;
    private String password;
    
    private boolean anonymous;
    private boolean loggedIn = false;
    
    private Operator operator = new Operator(null, null, null);

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

    public Operator getOperator() {
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
        List<Operator> userlist = Webapplication.getInstance().getUserlist();
        for (Operator o : userlist) {
            if(o.equals(this.operator)) {
                if (o.getPhonenumber() == null) {
                    anonymous = true;
                } else {
                    anonymous = false;
                }
                loggedIn = true;
                return "index.xhtml";
            }
        }
        loggedIn = false;
        return "error.xhtml";
    }
    
    
}
