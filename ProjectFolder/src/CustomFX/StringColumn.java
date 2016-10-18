/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomFX;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 *
 * @author richard
 */
public class StringColumn extends TableColumn implements VirtualColumn {

    private int index = 0;
    public StringColumn(String columnName, int index) {
        super(columnName);
        this.index = index;
        initialize();
    }
    
    @Override
    public void initialize() {
        setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>(){
            @Override
            public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(index).toString());
            }
        });
    }
}
