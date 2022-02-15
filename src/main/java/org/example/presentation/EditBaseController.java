package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.business.BaseProduct;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.data.Selected;
import org.example.data.Serializator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class EditBaseController implements Initializable {
    /**
     * title text field
     */
    @FXML
    private TextField titleA;
    /**
     * rating text field
     */
    @FXML
    private TextField ratingA;
    /**
     * calories text field
     */
    @FXML
    private TextField caloriesA;
    /**
     * protein text field
     */
    @FXML
    private TextField proteinA;
    /**
     * fat text field
     */
    @FXML
    private TextField fatA;
    /**
     * sodium text field
     */
    @FXML
    private TextField sodiumA;
    /**
     * price text field
     */
    @FXML
    private TextField priceA;
    /**
     * edit button
     */
    @FXML
    private Button edit;

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
        if(e.getSource()==edit)
        {   BaseProduct prod=(BaseProduct) Selected.item;
            DeliveryService deliveryService= Serializator.deserialize();
            deliveryService.editBaseProduct(prod,titleA.getText(),Double.valueOf(ratingA.getText()),Integer.valueOf(caloriesA.getText()),Integer.valueOf(proteinA.getText()),Integer.valueOf(fatA.getText()),Integer.valueOf(sodiumA.getText()),Integer.valueOf(priceA.getText()));
            Serializator.serialize(deliveryService);
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
        MenuItem prod=Selected.item;
        titleA.setText(prod.getTitle());
        ratingA.setText(""+prod.getRating());
        caloriesA.setText(""+prod.getCalories());
        proteinA.setText(""+prod.getProtein());
        fatA.setText(""+prod.getFat());
        sodiumA.setText(""+prod.getSodium());
        priceA.setText(""+prod.computePrice());
    }
}
