package hu.unideb.inf.view;

import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Model;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
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
    
    ObservableList list =  FXCollections.observableArrayList("Action","Comedy","Children", 
                                                             "Drama","Sci-Fi","Thriller",
                                                             "Adventure","Animation","Fantasy",
                                                             "Romance","Crime","Horror",
                                                             "Mystery","Documentary","War","Western");
    
    
    @FXML
    private ImageView Btn_Welcome,Btn_Search,Btn_Info,Btn_SearchFinder,Btn_Exit,Btn_Play;
    
    @FXML
    private ListView GenreListView;
    
    @FXML
    private TextField YearTextField1,YearTextField2;    
    
    @FXML
    private TextField Alarm;
    
    @FXML
    private AnchorPane Welcome,Search,Info;
     
    @FXML 
    private void handleButtonAction(MouseEvent event){
        
        if(event.getTarget() == Btn_Welcome){
            Welcome.setVisible(true);
            Search.setVisible(false);
            Info.setVisible(false);
        }
        
        if(event.getTarget() == Btn_Search){
            Search.setVisible(true);
            Welcome.setVisible(false);
            Info.setVisible(false);
        }
        
        if(event.getTarget() == Btn_Info){
            Info.setVisible(true);
            Welcome.setVisible(false);
            Search.setVisible(false);    
        }
        
        if(event.getTarget() == Btn_Play){
            Search.setVisible(true);
            Welcome.setVisible(false);
            Info.setVisible(false);
        }if(event.getTarget()==Btn_Exit){
        Search.setVisible(false);
        Welcome.setVisible(false);
        Info.setVisible(false);
        
        }
        
        if(event.getTarget() == Btn_SearchFinder){
            other();
        }
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       GenreListView.getItems().addAll(list);
       GenreListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    
     
    public void other(){
        try{
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/FXMLDisplayer.fxml"));
            Parent root = (Parent)loader.load();
            FXMLDisplayerController otherController = loader.getController();
            
            
            List<String> listan = GenreListView.getSelectionModel().getSelectedItems();
            
            
            if(listan.isEmpty() && (YearTextField1.getText().isEmpty() || YearTextField1.getText().isBlank()) && (YearTextField2.getText().isEmpty() || YearTextField2.getText().isBlank())){
                otherController.searchAll();
            }
            else{
                if(listan.isEmpty() && (YearTextField1.getText().isEmpty() || YearTextField1.getText().isBlank())){
                    otherController.searchYearMax(YearTextField2.getText());
                }
                else{
                    if(listan.isEmpty()){
                        if((YearTextField1.getText().isEmpty() || YearTextField1.getText().isBlank())){
                            otherController.searchYearMax(YearTextField2.getText());
                        }
                        else{
                            if((YearTextField2.getText().isEmpty() || YearTextField2.getText().isBlank())){
                                otherController.searchYearMin(YearTextField1.getText());
                            }
                            else{
                                otherController.searchMaxMin(YearTextField1.getText(),YearTextField2.getText());
                            }
                        }
                    }
                    else{
                        if((YearTextField1.getText().isEmpty() || YearTextField1.getText().isBlank()) && (YearTextField2.getText().isEmpty() || YearTextField2.getText().isBlank())){
                            otherController.searchGenre(listan);
                        }
                        else{
                            if(YearTextField1.getText().isEmpty() || YearTextField1.getText().isBlank()){
                                otherController.searchYearMaxGenre(listan,YearTextField2.getText());
                            }
                            else{
                                if(YearTextField2.getText().isEmpty() || YearTextField2.getText().isBlank()){
                                    otherController.searchYearMinGenre(listan,YearTextField1.getText());
                                }
                                else{
                                    otherController.searchYearMinMaxGenre(listan,YearTextField1.getText(),YearTextField2.getText());
                                }
                            }
                        }
                    }
                }
            }
            
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


            }catch (IOException ex) {
                Logger.getLogger(FXMLMovieSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
     
    }