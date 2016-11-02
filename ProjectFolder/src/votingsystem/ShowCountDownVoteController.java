/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import CustomFX.StringColumn;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class ShowCountDownVoteController implements Initializable {

    @FXML
    private TableView<String> categoryTableView;
    @FXML
    private TextField nameVoteCriterion;
    
    boolean updated = false;
    int clastID = -1, lastID = -1, cTotalID = -1, totalID = -1, totalVote = -1, cTotalVote = -1;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadData();
        } catch (SQLException ex) {
            Logger.getLogger(ShowCountDownVoteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    loadData();
                } catch (SQLException ex) {
                    Logger.getLogger(ShowCountDownVoteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    @FXML
    private void nameSearchQuery(KeyEvent event) {
    }

    private void loadData() throws SQLException {

        ObservableList data = null;
        data = FXCollections.observableArrayList();
        ResultSet rS = ConnectionController.fetchPosterVotes();
        
        cTotalID = 0;
        cTotalVote = 0;
        while (rS.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(rS.getString(1));
            row.add(rS.getString(2));
            row.add(rS.getString(3));
            data.add(row);
            
            cTotalVote += Integer.parseInt(rS.getString(3));
            clastID = Integer.parseInt(rS.getString(1));
            cTotalID += clastID;
        }
        
        if (lastID != clastID || cTotalID != totalID || totalVote != cTotalVote) {
            updated = true;
        }
        
        lastID = clastID;
        totalID = cTotalID;
        totalVote = cTotalVote;
        
        if (updated) {
            categoryTableView.getColumns().clear();
            StringColumn id = new StringColumn("ID", 0);
            StringColumn name = new StringColumn("Name", 1);
            StringColumn vote = new StringColumn("Vote Count", 2);

            categoryTableView.getColumns().add(id);
            categoryTableView.getColumns().add(name);
            categoryTableView.getColumns().add(vote);

            categoryTableView.setItems(data);
            updated = false;
        } 
    }
}
