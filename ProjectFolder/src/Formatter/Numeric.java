/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formatter;

/**
 *
 * @author Richard
 */
import java.util.function.UnaryOperator;
 
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class Numeric {
 
 
    public static TextFormatter<String> getTextFormatter() {
        UnaryOperator<Change> filter = getFilter();
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        return textFormatter;
    }
 
    private static UnaryOperator<Change> getFilter() {
        return change -> {
            String text = change.getText();
            if (text.matches("[0-9]*")) {
                return change;
            }
            return null;
         };
    }
 
}
