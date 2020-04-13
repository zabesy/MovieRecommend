package hu.unideb.inf.view;

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
    
    
    @FXML
    private TextField genreField;

    @FXML
    private TextField yearField;

    @FXML
    void newStage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MovieRecommend/src/main/resources/fxml/FXMLDisplayer.fxml"));
            Parent root = (Parent)loader.load();
            FXMLDisplayerController otherController = loader.getController();
            //Which 'search' function to be used
            otherController.setGenreField(genreField.getText());
            otherController.setYearField(yearField.getText());
            /*if(genreField.getText().isEmpty()){
                otherController.search(Integer.parseInt(yearField.getText()));
            }
            else{
                if(yearField.getText().isEmpty()){
                    otherController.search(genreField.getText());
                }
                else{
                    otherController.search(genreField.getText(),Integer.parseInt(yearField.getText()));
                }
            }*/
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMovieSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

}
