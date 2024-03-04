import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class NetController implements Serializable {

    private int id;
    private String location;
    private int size;
    
    private Ghostnet newNet = null;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void reportNet() {
        newNet = new Ghostnet(id, location, size);
        Webapplication.getInstance().saveNetToDB(newNet);
    }
    
    
    
  
    
    
}
