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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

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
    }

    @FXML
    private void nameSearchQuery(KeyEvent event) {
    }

    private void loadData() throws SQLException {

        ObservableList data = null;
        data = FXCollections.observableArrayList();
        ResultSet rS = ConnectionController.fetchPosterVotes();
        
        categoryTableView.getColumns().clear();
        while (rS.next()) {
            ObservableList<String> row = FXCollections.observableArrayList();
            row.add(rS.getString(1));
            row.add(rS.getString(2));
            data.add(row);
        }
        
        StringColumn name = new StringColumn("Name", 0);
        StringColumn vote = new StringColumn("Vote Count", 1);
        
        categoryTableView.getColumns().add(name);
        categoryTableView.getColumns().add(vote);
        categoryTableView.setItems(data);
    }
}
