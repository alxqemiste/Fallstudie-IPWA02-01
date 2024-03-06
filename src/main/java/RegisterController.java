import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private String operatorName;
    private String password;
    private String phonenumber;
    
    private Operator operator = null;

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

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

   
    

    public String register() {
        List<Operator> operatorList = Webapplication.getInstance().getOperatorList();

        if(operatorList.isEmpty()) {
            operator = new Operator(operatorName, password, phonenumber);
            Webapplication.getInstance().saveOperatorToDB(operator);
        } else {
            for (Operator o : operatorList) {

                if(o.getOperatorName().equals(operatorName)) {
                    //TODO Errorhandling
                    return "error.xhtml";
                } else {
                    operator = new Operator(operatorName, password, phonenumber);
                    Webapplication.getInstance().saveOperatorToDB(operator);


                }
            }
            
        }
        
        
        return "login.xhtml";
         
        
    }

}
