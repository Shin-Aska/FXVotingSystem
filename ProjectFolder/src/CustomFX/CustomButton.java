/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFX;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import votingsystem.GenerateTicketsController;
import votingsystem.MainFormController;

/**
 *
 * @author Richard
 */
public class CustomButton extends TableColumn implements VirtualColumn{ 
    
    private String symbol = "?";
    private EventHandler <ActionEvent> evt;
    
    public CustomButton(String columnName, String symbol, EventHandler <ActionEvent> evt) {
        super(columnName);
        this.symbol = symbol;
        this.evt = evt;
        initialize();
    }
    
    @Override
    public void initialize() {
        setCellFactory(new Callback<TableColumn<ObservableList<String>, String>, TableCell<ObservableList<String>, String>>() {
            
            public TableCell call(final TableColumn <ObservableList<String>, String> param) {
                
                final TableCell<ObservableList<String>, String> cell = new TableCell<ObservableList<String>, String>() {
                    
                    final Button btn = new Button(symbol);
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction((ActionEvent event) -> {
                               
                                GenerateTicketsController.selectedID = Integer.parseInt(getTableView().getItems().get( getIndex() ).get(0));
                                GenerateTicketsController.selectedIndex = MainFormController.selectedIndex = getIndex();
                                evt.handle(event);
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                
                return cell;
            }
        });
    }
}
