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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class FXMLMovieSceneController implements Initializable {
    
    private Model model;
    
    public void setModel(Model model){
        this.model = model;
    }
    
    @FXML
    private ImageView Btn_Welcome,Btn_Search,Btn_Info,Btn_SearchFinder,Btn_Exit,Btn_Play;
    
    
     @FXML
    private AnchorPane Welcome,Search,Info;
     
      @FXML 
    private void handleButtonAction(MouseEvent event){
        
        if(event.getTarget() == Btn_Welcome){
            Welcome.setVisible(true);
            Search.setVisible(false);
            Info.setVisible(false);
            
        }else
            if(event.getTarget() == Btn_Search){
            Search.setVisible(true);
            Welcome.setVisible(false);
            Info.setVisible(false);
            
            }else 
                if(event.getTarget() == Btn_Info){
                Info.setVisible(true);
                Welcome.setVisible(false);
                Search.setVisible(false);
                
                }else
                    if(event.getTarget() == Btn_Play){
                    Search.setVisible(true);
                    Welcome.setVisible(false);
                    Info.setVisible(false);
                    
                    }
    
    }
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
          /*  Scene scene = new scene(root);
            scene.setScene ;
            scene.show()*/

  
            
     /*   } catch (IOException ex) {
            Logger.getLogger(FXMLMovieSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

  
