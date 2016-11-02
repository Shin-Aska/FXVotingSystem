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
import java.text.ParseException;
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
import javafx.scene.control.ComboBox;
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
    
    public static int selectedID = 0, selectedIndex = 0;
    public static String selectedName = "";
    @FXML
    private ComboBox<String> selectedCategory;
    @FXML
    private MenuItem showVoteCount;
    
    /**************************************************/
    // Controller Processes
    
    public void setAdmin(boolean yes) {
        admin = yes;
    }
    
    public void resetLabel() {
        postIDNumberLabel.setText("[None Selected]");
    }
    
    public void showAdminProcess() {
        if (!admin) {
            fileMenu.setVisible(false);
            showVoteCount.setVisible(false);
        }
        else {
            fileMenu.setVisible(true);
            showVoteCount.setVisible(true);
        }
    }
    
    public void showConnectedProcess() {
        if (!connected) {
            voteThisButton.setVisible(false);
            postIDLabel.setVisible(false);
            postIDNumberLabel.setVisible(false);
            adminLogin.setVisible(false);
            connectButton.setText("Connect");
        }
        else {
            voteThisButton.setVisible(true);
            postIDLabel.setVisible(true);
            postIDNumberLabel.setVisible(true);
            adminLogin.setVisible(true);
            connectButton.setText("Disconnect");
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
                        
                        String cat = selectedCategory.getSelectionModel().getSelectedItem();
                        ResultSet rS = ConnectionController.fetchPosters(cat);
                        int lid = 0, lcount = 0;
                        Candidate.list.clear();
                        
                        while (rS.next()) {
                            ObservableList<String> row = FXCollections.observableArrayList();
                            row.add(rS.getString(2));
                            row.add(rS.getString(3));
                            Candidate.list.add(new Candidate(rS.getString(1), rS.getString(2), rS.getString(3)));
                            lid = Integer.parseInt(rS.getString(2));
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
        
        selectedCategory.getItems().add("Qualitative");
        selectedCategory.getItems().add("Quantitative");
        selectedCategory.getItems().add("All");
        selectedCategory.getSelectionModel().select(0);
    }    
    
    

    @FXML
    private void serverLocationWinSpawn(ActionEvent event) throws IOException {
        
        if (!connected) {
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
        else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Action ignored");
            alert.setContentText("You cannot change the Server Location while the program is still connected. Please close the connection and try again.");
            alert.showAndWait();
        }  
    }

    @FXML
    private void adminLoginWinSpawn(ActionEvent event) throws IOException {
        
        if (connected) {
            
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
        else {
            
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Illegal Action detected");
            alert.setContentText("This action requires an active connection. Please try to connect to an appropriate server first!");
            alert.showAndWait();
        }
        
    }

    @FXML
    private void connectAction(ActionEvent event) {
        
        try {
            
            if (!connected) {
                ConnectionController.connect();
                connected = true;

                statusLabel.setText(Configuration.ipaddress);
                postIDNumberLabel.setText("[None Selected]");
                selectedID = 0;
                selectedName = "";
            }
            else {
                ConnectionController.disconnect();
                connected = false;
                
                statusLabel.setText("[Not connected]");
                postIDNumberLabel.setText("");
                selectedID = 0;
                selectedName = "";
                posterTableView.getColumns().clear();
                
                
                lastID = 0;
                lastNumber = 0;
                admin = false;
            }
            
            showAdminProcess();
            showConnectedProcess();
                
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
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                resetLabel();
            }
        });
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
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    selectedID = 0;
                    selectedName = "";
                    resetLabel();
                }
            });
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

    @FXML
    private void showVoteWindow(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("showCountDownVote.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.NONE);
        stage.initOwner( voteThisButton.getScene().getWindow() );
        stage.initStyle(StageStyle.UNIFIED);
        stage.setScene(scene); 
        stage.show();
    }
}
