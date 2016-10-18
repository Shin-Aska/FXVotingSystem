/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import CustomFX.ButtonColumn;
import CustomFX.StringColumn;
import StringUtilities.RandomStringGenerator;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import static votingsystem.MainFormController.selectedID;
import static votingsystem.MainFormController.selectedName;

/**
 * FXML Controller class
 *
 * @author Richard
 */
public class GenerateTicketsController implements Initializable {

    @FXML
    private TextField ticketsField;
    @FXML
    private TableView<String> ticketsTableView;
    
    private boolean updated = false;
    private int lastID, lastNumber;
    private static final char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ticketsField.setTextFormatter(Formatter.Numeric.getTextFormatter());
    
        Timeline tableLoader = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    ObservableList data = FXCollections.observableArrayList();
                    ResultSet rS = ConnectionController.fetchTickets();
                    int lid = 0, lcount = 0;
                    
                    while (rS.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (int i = 1; i <= rS.getMetaData().getColumnCount(); i++) {

                            row.add(rS.getString(i));
                            lid = Integer.parseInt(rS.getString(1));
                        }
                        lcount += 1;
                        data.add(row);
                    }
                    
                    if (lcount != lastNumber || lid != lastID) {
                        updated = true;
                        lastNumber = lcount;
                        lastID = lid;
                    }
                    
                    if (updated) {
                        
                        updated = false;
                        ticketsTableView.getColumns().clear();
                            
                        StringColumn icol = new StringColumn("Ticket ID", 0);
                        ticketsTableView.getColumns().add(icol);
                            
                        StringColumn ncol = new StringColumn("Value", 1);
                        ticketsTableView.getColumns().add(ncol);
                            
                        ButtonColumn chooser = new ButtonColumn("Action", "X", new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                    
                                String id = Integer.toString(selectedID);
                                try {
                                    ConnectionController.removeTicket(id);
                                } catch (SQLException ex) {
                                    Logger.getLogger(GenerateTicketsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                            
                        ticketsTableView.getColumns().add(chooser);
                        ticketsTableView.setItems(data);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }));
        
        tableLoader.setCycleCount(Timeline.INDEFINITE);
        tableLoader.play();
    }    

    @FXML
    private void generateTickets(ActionEvent event) {
        
        int vlim = Integer.parseInt(ticketsField.getText());
        for (int i = 0; i < vlim; i++) {
            String id = RandomStringGenerator.randomString(CHARSET_AZ_09, 5);
            try {
                ConnectionController.addTicket(id);
            } catch (SQLException ex) {
               i -= 1;
            }
        }
        
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setHeaderText("Operation successfull");
        msg.setContentText(vlim + " tickets where successfully created.");
        msg.show();
    }
    
}
