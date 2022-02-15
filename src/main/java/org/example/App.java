package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.business.DeliveryService;
import org.example.business.User;
import org.example.data.Serializator;
import org.example.data.UserType;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;

/**
 * @author Sergiu
 *
 */
public class App extends Application
{

    /**
     *
     * @param stage the stage of the interface
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(new File("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Start.fxml").toURI().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Food Delivery");
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args the arguments of the main
     * @throws InterruptedException
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
       launch(args);

        /*LinkedHashSet<User> users=new LinkedHashSet<User>();
       User user1=new User("Sergiu","12", UserType.ADMIN);
        User user2=new User("Sergiu","12", UserType.CLIENT);
        User user3=new User("Sergiu","12", UserType.EMPLOYEE);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        DeliveryService deliveryService=new DeliveryService();
        deliveryService.setListOfUsers(users);
        Serializator.serialize(deliveryService);
      /**/
    }
}
