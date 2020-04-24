package hu.unideb.inf.view;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLMovieSceneController implements Initializable {
    
    private Model model;
    
    public void setModel(Model model){
        this.model = model;
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    /*@FXML
    void newStage(ActionEvent event) {
        try {
          Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMovieScene.fxml"));
            //Parent root = (Parent)loader.load();
           // FXMLDisplayerController otherController = loader.getController();
            
            //Which 'search' function to be used
            
         /*   if(!(this.genreField.getText().isEmpty()) && !(this.yearField.getText().isEmpty())){
            otherController.search(this.genreField.getText(), Integer.parseInt(this.yearField.getText()));
            }
            else{
                if(this.genreField.getText().isEmpty()){
                    otherController.search(Integer.parseInt(this.yearField.getText()));
                }
                else{
                otherController.search(this.genreField.getText());
                }
            }*/
           /* Scene scene = new scene(root);
            scene.setScene ;
            scene.show();*/

  
            
     /*   } catch (IOException ex) {
            Logger.getLogger(FXMLMovieSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

  
