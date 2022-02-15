package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.business.DeliveryService;
import org.example.data.Selected;
import org.example.data.Serializator;
import org.example.business.User;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class RegisterController implements Initializable {
    /**
     * username text field
     */
    @FXML
    private TextField username2;
    /**
     * password text field
     */
    @FXML
    private PasswordField password2;
    /**
     * button to register
     */
    @FXML
    private Button register2;
    /**
     *  button to go back to login
     */
    @FXML
    private Button backRegister;

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
        if (e.getSource() == register2) {
            User user = new User(username2.getText(), password2.getText(), Selected.type);
            DeliveryService deliveryService = Serializator.deserialize();
            LinkedHashSet<User> users = deliveryService.getListOfUsers();
            if (!users.add(user)) {
                JOptionPane.showMessageDialog(null,"The username is already taken!");
            } else {
                JOptionPane.showMessageDialog(null,"Register was successful!");
                deliveryService.setListOfUsers(users);
                Serializator.serialize(deliveryService);
            }
        }
        if(e.getSource()==backRegister)
        {
            open(backRegister,"D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Login.fxml");
        }

    }

    /**
     *
     * @param but the button of the window to be closed
     * @param pathName the name of the fxml file
     */
    private void open(Button but, String pathName) {
        try {
            Stage st = (Stage) but.getScene().getWindow();
            st.close();
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
     * it initializes the interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
