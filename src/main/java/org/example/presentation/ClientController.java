package org.example.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.business.CompositeProduct;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.data.Selected;
import org.example.data.Serializator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class ClientController implements Initializable {
    /**
     * button to view the items
     */
    @FXML
    private Button view;
    /**
     * table where the items from the menu can be seen
     */
    @FXML
    private TableView<MenuItem> table;
    /**
     *  title column of the table
     */
    @FXML
    private TableColumn<MenuItem, String> titleCol;
    /**
     * rating column of the table
     */
    @FXML
    private TableColumn<MenuItem, Double> ratingCol;
    /**
     * calories column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> caloriesCol;
    /**
     * protein column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> proteinCol;
    /**
     * fat column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> fatCol;
    /**
     * sodium column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> sodiumCol;
    /**
     * price column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceCol;
    /**
     * button to add the selected item in the cart
     */
    @FXML
    private Button add;
    /**
     *  the box that contains the items from the cart
     */
    @FXML
    private ComboBox<String> box;
    /**
     *  button to remove an item from the cart
     */
    @FXML
    private Button remove;
    /**
     * text field for writing the title
     */
    @FXML
    private TextField title;
    /**
     * text field for writing the rating
     */
    @FXML
    private TextField rating;
    /**
     * text field for writing the calories
     */
    @FXML
    private TextField calories;
    /**
     * text field for writing the protein
     */
    @FXML
    private TextField protein;
    /**
     * text field for writing the fat
     */
    @FXML
    private TextField fat;
    /**
     * text field for writing the sodium
     */
    @FXML
    private TextField sodium;
    /**
     *  text field for writing the price
     */
    @FXML
    private TextField price;
    /**
     * button to make the order
     */
    @FXML
    private Button order;
    /**
     * button to search an item
     */
    @FXML
    private Button searchy;
    /**
     *  button to generate bills
     */
    @FXML
    private Button generateBill;
    /**
     * button to see the composition of the item that is selected
     */
    @FXML
    private Button seeCompo;
    /**
     * the text area where you can see the composition of the item selected
     */
    @FXML
    private TextArea textArea;
    /**
     *  button to clear the text area
     */
    @FXML
    private Button clear;

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {

        if (e.getSource() == view) {
            DeliveryService deliveryService = Serializator.deserialize();
            if (deliveryService.getMyMenu() != null) {
                ObservableList<MenuItem> data =
                        FXCollections.observableArrayList(
                                deliveryService.getMyMenu()
                        );
                table.setItems(data);
            }
        }
        if (e.getSource() == add) {
            MenuItem prod = table.getSelectionModel().getSelectedItem();
            if(prod!=null) {
                String prodString = prod.toString();
                box.getItems().add(prodString);
            }

        }
        if (e.getSource() == remove) {
            box.getItems().remove(box.getSelectionModel().getSelectedItem());
        }

        if (e.getSource() == searchy) {
            DeliveryService deliveryService= Serializator.deserialize();
            LinkedHashSet<MenuItem> list=deliveryService.searchItem(title.getText(),rating.getText(),calories.getText(),protein.getText(),fat.getText(),sodium.getText(),price.getText());
            ObservableList<MenuItem> data =
                    FXCollections.observableArrayList(
                            list
                    );

           table.setItems(data);
        }
        if (e.getSource() == order) {

            DeliveryService deliveryService= Serializator.deserialize();
            ArrayList<MenuItem> menuItems=new ArrayList<>();
            ArrayList<String> items=new ArrayList<>();
            if(box.getItems()!=null) {
                for( String st: box.getItems())
                {
                    items.add(st);
                }
                for (String s : items) {
                    for (MenuItem m : deliveryService.getMyMenu()) {
                        if (s.equals(m.toString())) {
                            menuItems.add(m);
                        }
                    }
                }
                deliveryService.addObserver(Selected.employeeController);
                deliveryService.createOrder(menuItems);
            }
            Serializator.serialize(deliveryService);
        }
        if(e.getSource()==generateBill)
        {
            DeliveryService deliveryService=Serializator.deserialize();
            deliveryService.generateBill();
        }
        if(e.getSource()==seeCompo)
        {
            if(table.getSelectionModel().getSelectedItem().getClass().getSimpleName().equals("CompositeProduct"))
            {   textArea.setText("");
                MenuItem prod=table.getSelectionModel().getSelectedItem();
                CompositeProduct pr= (CompositeProduct) prod;
                for(MenuItem p: pr.getCompositeProduct())
                {
                    textArea.appendText(p+"\n");

                }
            }
            else
            {
                textArea.setText(table.getSelectionModel().getSelectedItem().toString());
            }
        }
        if(e.getSource()==clear)
        {
            textArea.setText("");
        }

    }

    /**
     *
     * @param url url
     * @param resourceBundle resourceBundle
     * initializes the client interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleCol.setCellValueFactory(new PropertyValueFactory<MenuItem, String>("title"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Double>("rating"));
        caloriesCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("calories"));
        proteinCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("protein"));
        fatCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("fat"));
        sodiumCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("sodium"));
        priceCol.setCellValueFactory(new PropertyValueFactory<MenuItem, Integer>("price"));
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
