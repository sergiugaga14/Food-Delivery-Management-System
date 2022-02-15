package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.data.Selected;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static org.example.data.UserType.*;

/**
 * @author Sergiu
 */
public class Controller implements Initializable {
    /**
     * button for opening the admin window
     */
    @FXML
    private Button admin;
    /**
     * button for opening the employee window
     */
    @FXML
    private Button employee;
    /**
     * button for opening the client window
     */
    @FXML
    private Button client;


    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     * it handles the buttons from the start interface
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
        if (e.getSource() == admin) {
            Selected.type = ADMIN;
            open(admin, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Login.fxml");
        }
        if (e.getSource() == client) {
            Selected.type = CLIENT;
            open(admin, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Login.fxml");
        }
        if (e.getSource() == employee) {
            Selected.type = EMPLOYEE;
            open(admin, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Login.fxml");

        }

    }

    /**
     *
     * @param but the button of the interface to be close
     * @param pathName the name of the fxml file to be opened
     *  it closes the current window, and opens a new one
     */
    private void open(Button but, String pathName) {
        try {
            FXMLLoader loader = new FXMLLoader(new File(pathName).toURI().toURL());
            Parent root1 = (Parent) loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception ev) {
            ev.printStackTrace();
        }
    }

    /**
     *
     * @param url
     * @param resourceBundle
     * initializes the start interface
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
