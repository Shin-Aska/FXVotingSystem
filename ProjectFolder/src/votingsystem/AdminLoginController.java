/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class AdminLoginController implements Initializable {

    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    
    private MainFormController parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginAction(ActionEvent event) throws SQLException {
        
        Node node = (Node)event.getSource();
        if (ConnectionController.validateUser(usernameTextField.getText(), passwordTextField.getText())) {
            Alert msg = new Alert(AlertType.INFORMATION);
            msg.setHeaderText("Login successful");
            msg.setContentText("Welcome! You now have administrator access on this system. Please check the file menu for more additional functionalities");
            msg.initModality(Modality.WINDOW_MODAL);
            msg.initOwner(node.getScene().getWindow());
            msg.initStyle(StageStyle.UNIFIED);
            parent.setAdmin(true);
            parent.showAdminProcess();
            msg.showAndWait();
            node.getScene().getWindow().hide();
        }
        else {
            Alert msg = new Alert(AlertType.ERROR);
            msg.setHeaderText("Invalid user credentails");
            msg.setContentText("The program did not find any accounts under this username and password combination");
            msg.initModality(Modality.WINDOW_MODAL);
            msg.initOwner(node.getScene().getWindow());
            msg.initStyle(StageStyle.UNIFIED);
            parent.setAdmin(false);
            msg.showAndWait();
        }
        
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Node node = (Node)event.getSource();
        node.getScene().getWindow().hide();
    }

    void setParent(MainFormController aThis) {
        parent = aThis;
    }
    
}
