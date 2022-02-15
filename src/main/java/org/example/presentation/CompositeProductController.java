package org.example.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.business.BaseProduct;
import org.example.business.CompositeProduct;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.data.Serializator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class CompositeProductController implements Initializable {
    /**
     * table to see the items from the menu
     */
    @FXML
    private TableView<MenuItem> table;
    /**
     *  title column
     */
    @FXML
    private TableColumn<MenuItem, String> titleColA;
    /**
     * rating column
     */
    @FXML
    private TableColumn<MenuItem, Double> ratingColA;
    /**
     * calories column
     */
    @FXML
    private TableColumn<MenuItem, Integer> caloriesColA;
    /**
     *  protein column
     */
    @FXML
    private TableColumn<MenuItem, Integer> proteinColA;
    /**
     *  fat column
     */
    @FXML
    private TableColumn<MenuItem, Integer> fatColA;
    /**
     *  sodium column
     */
    @FXML
    private TableColumn<MenuItem, Integer> sodiumColA;
    /**
     *   price column
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceColA;
    /**
     * table for seeing the items from the list
     */
    @FXML
    private TableView<MenuItem> table2;
    /**
     * title column
     */
    @FXML
    private TableColumn<MenuItem, String> titleColA1;
    /**
     * rating column
     */
    @FXML
    private TableColumn<MenuItem, Double> ratingColA1;
    /**
     * calories column
     */
    @FXML
    private TableColumn<MenuItem, Integer> caloriesColA1;
    /**
     * protein column
     */
    @FXML
    private TableColumn<MenuItem, Integer> proteinColA1;
    /**
     * fat column
     */
    @FXML
    private TableColumn<MenuItem, Integer> fatColA1;
    /**
     * sodium column
     */
    @FXML
    private TableColumn<MenuItem, Integer> sodiumColA1;
    /**
     *
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceColA1;
    /**
     * price column
     */
    @FXML
    private Button add;
    /**
     * add the item selected to the list
     */
    @FXML
    private Button remove;
    /**
     * removing from the list the item that is selected
     */
    @FXML
    private TextField titleA;
    /**
     * button to create the composite product
     */
    @FXML
    private Button create;
    /**
     * list of the items selected
     */
    private ArrayList<MenuItem> list=new ArrayList<>();

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     * handle the buttons from the interface
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
    if(e.getSource()==add)
    {
        MenuItem prod=table.getSelectionModel().getSelectedItem();
        list.add(prod);
        ObservableList<MenuItem> data =
                FXCollections.observableArrayList(
                        list
                );
        table2.setItems(data);
    }

    if(e.getSource()==remove)
    {
        MenuItem prod=table2.getSelectionModel().getSelectedItem();
        list.remove(prod);
        ObservableList<MenuItem> data =
                FXCollections.observableArrayList(
                        list
                );
        table2.setItems(data);
    }
    if(e.getSource()==create)
    {
        String title=titleA.getText();
       CompositeProduct prod= new CompositeProduct(title,list);
        DeliveryService deliveryService = Serializator.deserialize();
        deliveryService.createCompoProd(prod);
        Serializator.serialize(deliveryService);
    }

    }

    /**
     *
     * @param url url
     * @param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColA.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("title"));
        ratingColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("rating"));
        caloriesColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("calories"));
        proteinColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("protein"));
        fatColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("fat"));
        sodiumColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("sodium"));
        priceColA.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("price"));

        titleColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("title"));
        ratingColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("rating"));
        caloriesColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("calories"));
        proteinColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("protein"));
        fatColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("fat"));
        sodiumColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("sodium"));
        priceColA1.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("price"));

        DeliveryService deliveryService = null;
        try {
            deliveryService = Serializator.deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deliveryService.getMyMenu() != null) {
            ObservableList<MenuItem> data =
                    FXCollections.observableArrayList(
                            deliveryService.getMyMenu()
                    );
            table.setItems(data);
        }
    }
}
