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
import org.example.business.CompositeProduct;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.data.Selected;
import org.example.data.Serializator;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class EditCompoController implements Initializable {

    /**
     * table for menu items
     */
    @FXML
    private TableView<MenuItem> table;
    /**
     * title column
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
     * protein column
     */
    @FXML
    private TableColumn<MenuItem, Integer> proteinColA;
    /**
     * fat column
     */
    @FXML
    private TableColumn<MenuItem, Integer> fatColA;
    /**
     * sodium column
     */
    @FXML
    private TableColumn<MenuItem, Integer> sodiumColA;
    /**
     * price column
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceColA;
    /**
     * table for list items
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
     *  calories column
     */
    @FXML
    private TableColumn<MenuItem, Integer> caloriesColA1;
    /**
     *   protein column
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
     *  price column
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceColA1;
    /**
     *  button to add the item selected to the list
     */
    @FXML
    private Button add;
    /**
     * button to remove the item selected from the list
     */
    @FXML
    private Button remove;
    /**
     * text field for writing the title
     */
    @FXML
    private TextField titleA;
    /**
     * button to edit the composite product
     */
    @FXML
    private Button edit;
    /**
     * the list of items that compose the product
     */
    private ArrayList<MenuItem> list=new ArrayList<>();

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
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
        if(e.getSource()==edit)
        {   CompositeProduct prod=(CompositeProduct) Selected.item;
            DeliveryService deliveryService= Serializator.deserialize();
            deliveryService.editCompoProduct(prod,titleA.getText(),list);
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
        CompositeProduct prod=(CompositeProduct) Selected.item;
        ObservableList<MenuItem> data =
                FXCollections.observableArrayList(
                        prod.getCompositeProduct()
                );
        list.addAll(prod.getCompositeProduct());
        table2.setItems(data);
        titleA.setText(prod.getTitle());
    }
}
