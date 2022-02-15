package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

import static org.example.data.UserType.ADMIN;
import static org.example.data.UserType.CLIENT;

/**
 * @author Sergiu
 */
public class LoginController implements Initializable {
    /**
     * username text field
     */
    @FXML
    private TextField username;
    /**
     * password text field
     */
    @FXML
    private PasswordField password;
    /**
     * login button
     */
    @FXML
    private Button login;
    /**
     * register button
     */
    @FXML
    private Button register;

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {

        if (e.getSource() == login) {
            User userFormed = new User("", "", ADMIN);
            DeliveryService deliveryService = Serializator.deserialize();
            LinkedHashSet<User> users = deliveryService.getListOfUsers();
            userFormed.setUsername(username.getText());
            userFormed.setPassword(password.getText());
            userFormed.setType(Selected.type);
            int okay = 0;
            for (User user : users) {
                if (user.getUsername().equals(userFormed.getUsername()))
                    if (user.getPassword().equals(userFormed.getPassword()))
                        if (user.getType().equals(userFormed.getType())) {
                            okay = 1;
                            if(user.getType().equals(CLIENT)) {
                                Selected.id = user.getId();
                                Selected.user = user;
                            }
                            break;
                        }
            }
            if (okay == 0) JOptionPane.showMessageDialog(null, "Username or password are incorrect");
            else {
                if(Selected.type.equals(CLIENT))
                    open(login, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Client.fxml");
                else if(Selected.type.equals(ADMIN))
                {
                    open(login, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Admin.fxml");
                }
                else
                {
                    open(login, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Employee.fxml");
                }
            }

        }
        if (e.getSource() == register) {
            open(register, "D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Register.fxml");
        }


    }

    /**
     *
     * @param but button of the window to be closed
     * @param pathName name of the file
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
