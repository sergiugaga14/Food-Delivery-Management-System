package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.business.BaseProduct;
import org.example.business.DeliveryService;
import org.example.data.Serializator;

import java.io.IOException;

/**
 * @author Sergiu
 */
public class BaseProductController {
    /**
     * text field for writing the title
     */
    @FXML
    private TextField titleA;
    /**
     *text field for writing the rating
     */
    @FXML
    private TextField ratingA;
    /**
     * text field for writing the calories
     */
    @FXML
    private TextField caloriesA;
    /**
     * text field for writing the protein
     */
    @FXML
    private TextField proteinA;
    /**
     * text field for writing the fat
     */
    @FXML
    private TextField fatA;
    /**
     * text field for writing the sodium
     */
    @FXML
    private TextField sodiumA;
    /**
     * text field for writing the price
     */
    @FXML
    private TextField priceA;
    /**
     * button to create the base product
     */
    @FXML
    private Button create;

    /**
     *
     * @param e event of the interface
     * @throws IOException
     * @throws ClassNotFoundException
     * handles the action of the buttons
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
        if(e.getSource()==create) {
            BaseProduct prod = new BaseProduct(titleA.getText(), Double.valueOf(ratingA.getText()), Integer.valueOf(caloriesA.getText()), Integer.valueOf(proteinA.getText()), Integer.valueOf(fatA.getText()), Integer.valueOf(sodiumA.getText()), Integer.valueOf(priceA.getText()));
            DeliveryService deliveryService = Serializator.deserialize();
            deliveryService.createBaseProd(prod);
            Serializator.serialize(deliveryService);
        }



    }
}
