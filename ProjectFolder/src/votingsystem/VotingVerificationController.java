/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class VotingVerificationController implements Initializable {

    @FXML
    private TextField votersTextField;
    @FXML
    private Label votersStatus;
    @FXML
    private Label voterLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        votersTextField.textProperty().addListener((ov, oldValue, newValue) -> {
            votersTextField.setText(newValue.toUpperCase());
        });
    }    

    @FXML
    private void voterVerifier(KeyEvent event) throws SQLException {
        
        if (votersTextField.getText().length() >= 5) {
            if (ConnectionController.verifyVoterID(votersTextField.getText())) {
                
                ConnectionController.addVote(Integer.toString(MainFormController.selectedID));
                MainFormController.selectedID = 0;
                MainFormController.selectedName = "";
                voterLabel.setText("Action successful!");
                voterLabel.setStyle("-fx-text-fill: green");
                votersTextField.setDisable(true);
                Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        votersTextField.getScene().getWindow().hide();
                    }
                }));

                timeLine.setCycleCount(1);
                timeLine.play();
                
            }
            else {
                
                votersTextField.setText("");
                voterLabel.setText("No Voter ID with that combination found. Please try again");
                voterLabel.setStyle("-fx-text-fill: red");
                Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        voterLabel.setText("Please enter your voting code");
                        voterLabel.setStyle("-fx-text-fill: black");
                    }
                }));

                timeLine.setCycleCount(1);
                timeLine.play();
            }
        }
    }
    
}
