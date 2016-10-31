/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import Formatter.Numeric;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class ItemSetupController implements Initializable {

    @FXML
    private TextField posterNameField;
    @FXML
    private TextField posterIDField;
    @FXML
    private ComboBox<String> categorySelector;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            posterIDField.setTextFormatter(Numeric.getTextFormatter());
            posterIDField.setText(ConnectionController.getNextPosterID("Qualitative").toString());
            categorySelector.getItems().add("Qualitative");
            categorySelector.getItems().add("Quantitative");
            categorySelector.getSelectionModel().select(0);
        } catch (SQLException ex) {
            Logger.getLogger(ItemSetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addItemEvent(ActionEvent event) {
        try {
            ConnectionController.addPoster(posterIDField.getText(), posterNameField.getText(), categorySelector.getSelectionModel().getSelectedItem());
            ((Node)event.getSource()).getScene().getWindow().hide();
            
            Alert msg = new Alert(Alert.AlertType.INFORMATION);
            msg.initModality(Modality.WINDOW_MODAL);
            msg.initOwner(((Node)event.getSource()).getScene().getWindow());
            msg.initStyle(StageStyle.UNIFIED);
            msg.setContentText("The poster has been added to the database");
            msg.setHeaderText("Success!");
            msg.show();
            
        } catch (SQLException ex) {
            Alert msg = new Alert(Alert.AlertType.ERROR);
            msg.initModality(Modality.WINDOW_MODAL);
            msg.initOwner(((Node)event.getSource()).getScene().getWindow());
            msg.initStyle(StageStyle.UNIFIED);
            msg.setContentText("You may have done something not right with your input. Possibly a duplicate id of an existing poster. Please check your input and try again.");
            msg.setHeaderText("OOPS! There is something wrong with your input");
            msg.show();
            
            Logger.getLogger(ItemSetupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelActionEvent(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }

    @FXML
    private void onCategoryChange(ActionEvent event) throws SQLException {
        
        posterIDField.setText( ConnectionController.getNextPosterID( categorySelector.getSelectionModel().getSelectedItem() ).toString() );
    }
    
}
