/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package votingsystem;

import CustomFX.ButtonColumn;
import CustomFX.StringColumn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Richard
 */
public class MainFormController implements Initializable {
    
    private Label label;
    private boolean admin = false;
    private boolean connected = false;
    private boolean updated = false;
    
    private int lastID, lastNumber;
    
    @FXML
    private Menu fileMenu;
    @FXML
    private Button voteThisButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label postIDLabel;
    @FXML
    private Label postIDNumberLabel;
    @FXML
    private MenuItem adminLogin;
    @FXML
    private Button connectButton;
    @FXML
    private TableView<?> posterTableView;
    
    
    // Selected values
    
    public static int selectedID = 0;
    public static String selectedName = "";
    
    /**************************************************/
    // Controller Processes
    
    public void setAdmin(boolean yes) {
        admin = yes;
    }
    
    public void showAdminProcess() {
        if (!admin) {
            fileMenu.setVisible(false);
        }
        else {
            fileMenu.setVisible(true);
        }
    }
    
    public void showConnectedProcess() {
        if (!connected) {
            voteThisButton.setVisible(false);
            postIDLabel.setVisible(false);
            postIDNumberLabel.setVisible(false);
            adminLogin.setVisible(false);
        }
        else {
            voteThisButton.setVisible(true);
            postIDLabel.setVisible(true);
            postIDNumberLabel.setVisible(true);
            adminLogin.setVisible(true);
        }
    }
    
    ////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                showAdminProcess();
                showConnectedProcess();
            }
        }));
        
        timeLine.setCycleCount(1);
        timeLine.play();
        
        Configuration.ipaddress = "127.0.0.1";
        Configuration.username  = "root";
        Configuration.password  = "";
        
        Timeline tableLoader = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    ObservableList data = null;
                    
                    if (connected) {
                        data = FXCollections.observableArrayList();
                        ResultSet rS = ConnectionController.fetchPosters();
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
                    }
                    
                    if (connected && updated) {
                        
                        updated = false;
                        posterTableView.getColumns().clear();
                            
                        StringColumn icol = new StringColumn("Poster ID", 0);
                        posterTableView.getColumns().add(icol);
                            
                        StringColumn ncol = new StringColumn("Poster Name", 1);
                        posterTableView.getColumns().add(ncol);
                            
                        ButtonColumn chooser = new ButtonColumn("Action", "Choose", new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event) {
                                    
                                postIDNumberLabel.setText(Integer.toString(selectedID) + " - " + selectedName);
                            }
                        });
                            
                        posterTableView.getColumns().add(chooser);
                        posterTableView.setItems(data);
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
    private void serverLocationWinSpawn(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Serverlocation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner( voteThisButton.getScene().getWindow() );
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void adminLoginWinSpawn(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminLogin.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner( voteThisButton.getScene().getWindow() );
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(scene); 
        stage.show();
        
        loader.<AdminLoginController>getController().setParent(this);
    }

    @FXML
    private void connectAction(ActionEvent event) {
        
        try {
            ConnectionController.connect();
            connected = true;
            showConnectedProcess();
            statusLabel.setText(Configuration.ipaddress);
            postIDNumberLabel.setText("[None Selected]");
            selectedID = 0;
            selectedName = "";
        }
        catch (Exception ex) {
            
            Alert msg = new Alert(AlertType.ERROR);
            msg.setHeaderText("A connection error occured");
            msg.setContentText("OOPS! There's something wrong with the connection:\n\n" + ex.getMessage());
            msg.show();
        }
    }

    @FXML
    private void newVoteWinSpawn(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ItemSetup.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner( voteThisButton.getScene().getWindow() );
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(scene); 
        stage.show();
    }

    @FXML
    private void voteActionEffect(ActionEvent event) throws IOException {
        
        if (selectedID != 0) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VotingVerification.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner( voteThisButton.getScene().getWindow() );
            stage.initStyle(StageStyle.UNIFIED);
            stage.setResizable(false);
            stage.setScene(scene); 
            stage.show();
        }
        else {
            
            Alert msg = new Alert(AlertType.WARNING);
            msg.setHeaderText("No selected poster detected");
            msg.setContentText("No action will be taken, please selected a poster");
            msg.show();
        }
            
    }

    @FXML
    private void newGenerateTicket(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GenerateTickets.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner( voteThisButton.getScene().getWindow() );
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(scene); 
        stage.show();
    }

    @FXML
    private void helpSection(ActionEvent event) {
        
        
    }
}
