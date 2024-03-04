import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private String username;
    private String password;
    private String phonenumber;
    
    private Operator newUser = null;

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

    public Operator getNewUser() {
        return newUser;
    }

    public void setNewUser(Operator newUser) {
        this.newUser = newUser;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

   
    

    public String register() {
                
        System.out.println(username +" " + password + " " + phonenumber );
       
        newUser = new Operator(username, password);
        Webapplication.getInstance().saveUserToDB(newUser);
        
        return "login.xhtml";
    }

}
