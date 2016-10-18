/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class ServerlocationController implements Initializable {

    @FXML
    private TextField subnet1;
    @FXML
    private TextField subnet2;
    @FXML
    private TextField subnet3;
    @FXML
    private TextField subnet4;
    @FXML
    private TextField dbUsername;
    @FXML
    private TextField dbPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subnet1.setTextFormatter(Formatter.Numeric.getTextFormatter());
        subnet2.setTextFormatter(Formatter.Numeric.getTextFormatter());
        subnet3.setTextFormatter(Formatter.Numeric.getTextFormatter());
        subnet4.setTextFormatter(Formatter.Numeric.getTextFormatter());
    
        String[] ipadds = Configuration.ipaddress.split("[.]");
       
        subnet1.setText(ipadds[0]);
        subnet2.setText(ipadds[1]);
        subnet3.setText(ipadds[2]);
        subnet4.setText(ipadds[3]);
        
        dbUsername.setText(Configuration.username);
        dbPassword.setText(Configuration.password);
    }    

    @FXML
    private void subnet1Input(KeyEvent event) throws Exception {
        
        if (subnet1.getText().length() > 2 && event.getCode() != KeyCode.BACK_SPACE) {
            subnet2.requestFocus();
        }
        
        if (event.getCode() == KeyCode.ENTER) {
            subnet2.requestFocus();
        }
    }

    @FXML
    private void subnet2Input(KeyEvent event) {
        if (subnet2.getText().length() > 2 && event.getCode() != KeyCode.BACK_SPACE) {
            subnet3.requestFocus();
        }
        
        if (event.getCode() == KeyCode.ENTER) {
            subnet3.requestFocus();
        }
    }

    @FXML
    private void subnet3Input(KeyEvent event) {
        if (subnet3.getText().length() > 2 && event.getCode() != KeyCode.BACK_SPACE) {
            subnet4.requestFocus();
        }
        
        if (event.getCode() == KeyCode.ENTER) {
            subnet4.requestFocus();
        }
    }

    @FXML
    private void subnet4Input(KeyEvent event) {
        if (subnet4.getText().length() > 2 && event.getCode() != KeyCode.BACK_SPACE) {
            dbUsername.requestFocus();
        }
        
        if (event.getCode() == KeyCode.ENTER) {
            dbUsername.requestFocus();
        }
    }

    @FXML
    private void submitAction(ActionEvent event) {
        
        
        if (subnet1.getText().isEmpty() || subnet2.getText().isEmpty() || subnet3.getText().isEmpty() || subnet4.getText().isEmpty() || dbUsername.getText().isEmpty()) {
            
            Alert msg = new Alert(AlertType.ERROR);
            msg.initModality(Modality.WINDOW_MODAL);
            msg.initOwner(((Node)event.getSource()).getScene().getWindow());
            msg.initStyle(StageStyle.UNIFIED);
            msg.setContentText("Empty inputs where found. Please try again.");
            msg.setHeaderText("OOPS! Settings are invalid.");
            msg.show();
        }
        else {
            Configuration.ipaddress = subnet1.getText() + "." + subnet2.getText() + "." + subnet3.getText() + "." + subnet4.getText();
            Configuration.username = dbUsername.getText();
            Configuration.password = dbPassword.getText();
            
            Alert msg = new Alert(AlertType.INFORMATION);
            msg.initModality(Modality.APPLICATION_MODAL);
            msg.initStyle(StageStyle.UNIFIED);
            msg.setContentText("Configuration is saved.. The program is now attempting to reconnect to the server");
            msg.setHeaderText("Your settings had been saved");
            msg.show();
            
            ((Node)event.getSource()).getScene().getWindow().hide();
        }
       
        
            
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
    
}
