package org.example.presentation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import org.example.data.Selected;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class EmployeeController implements Initializable, Observer {

    /**
     * text area to display the orders that were notified
     */
    @FXML
    private TextArea textArea;

    /**
     *
     * @param o
     * @param arg
     * it writes in the the text area the string given as argument
     */
    @Override
    public void update(Observable o, Object arg) {
        textArea.appendText(arg.toString());
    }

    /**
     *
     * @param url
     * @param resourceBundle
     * it initializes the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Selected.employeeController=this;
    }

}
