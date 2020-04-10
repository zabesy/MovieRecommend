package hu.unideb.inf.view;

import hu.unideb.inf.model.Model;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLMovieSceneController implements Initializable {
    
    private Model model;
    
    public void setModel(Model model){
        this.model = model;
    }
    
    @FXML
    private Label label;
    
    @FXML
    void handleButtonPushed(ActionEvent event) {
        System.out.println("You clicked me");
    }


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

}
