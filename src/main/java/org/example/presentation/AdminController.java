package org.example.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.business.BaseProduct;
import org.example.business.CompositeProduct;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.data.FileOperations;
import org.example.data.Selected;
import org.example.data.Serializator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ResourceBundle;

/**
 * @author Sergiu
 */
public class AdminController implements Initializable {

    /**
     * the button to see the items of the menu
     */
    @FXML
    private Button viewA;
    /**
     * the table in which the items are shown
     */
    @FXML
    private TableView<MenuItem> table2;
    /**
     * title column of the table
     */
    @FXML
    private TableColumn<MenuItem, String> titleColA;
    /**
     * rating column of the table
     */
    @FXML
    private TableColumn<MenuItem, Double> ratingColA;
    /**
     * calories column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> caloriesColA;
    /**
     * protein column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> proteinColA;
    /**
     *  fat column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> fatColA;
    /**
     * sodium column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> sodiumColA;
    /**
     *   price column of the table
     */
    @FXML
    private TableColumn<MenuItem, Integer> priceColA;
    /**
     * the button used for deleting an item
     */
    @FXML
    private Button removeA;
    /**
     *  text field used for writing the title
     */
    @FXML
    private TextField titleA;
    /**
     * text field used for writing the rating
     */
    @FXML
    private  TextField ratingA;
    /**
     * text field used for writing the calories
     */
    @FXML
    private TextField caloriesA;
    /**
     * text field used for writing the protein
     */
    @FXML
    private TextField proteinA;
    /**
     *text field used for writing the fat
     */
    @FXML
    private TextField fatA;
    /**
     * text field used for writing the sodium
     */
    @FXML
    private TextField sodiumA;
    /**
     * text field used for writing the price
     */
    @FXML
    private TextField priceA;
    /**
     * button used to import the items from the file
     */
    @FXML
    private Button importItem;
    /**
     * button used to search items based on some criteria
     */
    @FXML
    private Button searchA;
    /**
     * opens a window used for generating reports
     */
    @FXML
    private Button reports;
    /**
     * button used for editing a product
     */
    @FXML
    private Button editMenu;
    /**
     *  button used for creating a base product
     */
    @FXML
    private Button create;
    /**
     * button used for creating a composed product
     */
    @FXML
    private Button createC;

    /**
     * buttons used to see the composition of the item that is selected
     */
    @FXML
    private Button seeCompo;
    /**
     * the text area where you can see the composition of the item selected
     */
    @FXML
    private TextArea textArea;
    /**
     * button used for clearing the text area
     */
    @FXML
    private Button clear;

    /**
     *  list of items that were selected
     */
    private ArrayList<MenuItem> listOfItems=new ArrayList<>();

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     * method to handle the buttons from the interface
     */

    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
        if(e.getSource()==editMenu)
        {    MenuItem prod= table2.getSelectionModel().getSelectedItem();
             Selected.item=prod;
             if(prod.getClass().getSimpleName().equals("BaseProduct"))
             {
                 open("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\EditBase.fxml");
             }
             else
             {
                 open("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\EditCompo.fxml");
             }

        }

        if(e.getSource()==clear)
        {
            textArea.setText("");
        }


        if(e.getSource()==create)
        {
           open("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\BaseProduct.fxml");

        }
        if(e.getSource()==createC)
        {

            open("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\CompositeProduct.fxml");
        }
        if (e.getSource() == viewA) {
            DeliveryService deliveryService = Serializator.deserialize();
            if (deliveryService.getMyMenu() != null) {
                ObservableList<MenuItem> data =
                        FXCollections.observableArrayList(
                                deliveryService.getMyMenu()
                        );
                InitializeTable(data);
            }
        }
        if(e.getSource()==removeA)
        {
            MenuItem prod =table2.getSelectionModel().getSelectedItem();
            DeliveryService deliveryService=Serializator.deserialize();
            deliveryService.deleteMenuItem(prod);
            Serializator.serialize(deliveryService);
            if (deliveryService.getMyMenu() != null) {
                ObservableList<MenuItem> data =
                        FXCollections.observableArrayList(
                                deliveryService.getMyMenu()
                        );
                InitializeTable(data);
            }

        }
        if (e.getSource() == searchA) {
            DeliveryService deliveryService= Serializator.deserialize();
            LinkedHashSet<MenuItem> list=deliveryService.searchItem(titleA.getText(),ratingA.getText(),caloriesA.getText(),proteinA.getText(),fatA.getText(),sodiumA.getText(),priceA.getText());
            ObservableList<MenuItem> data =
                    FXCollections.observableArrayList(
                            list
                    );

            InitializeTable(data);

        }
        if(e.getSource()==seeCompo)
        {
            if(table2.getSelectionModel().getSelectedItem().getClass().getSimpleName().equals("CompositeProduct"))
            {   textArea.setText("");
                MenuItem prod=table2.getSelectionModel().getSelectedItem();
                CompositeProduct pr= (CompositeProduct) prod;
                for(MenuItem p: pr.getCompositeProduct())
                {
                    textArea.appendText(p+"\n");

                }
            }
            else
            {
                textArea.setText(table2.getSelectionModel().getSelectedItem().toString());
            }
        }
        if(e.getSource()==reports)
        {
            open("D:\\PT\\PT2021_30424_Gaga_Sergiu_Assignment_4\\src\\main\\java\\org\\example\\presentation\\Reports.fxml");
        }


        if(e.getSource()==importItem)
        {
            LinkedHashSet<MenuItem> products = FileOperations.readFile();
            DeliveryService deliveryService = Serializator.deserialize();
            deliveryService.importMenu(products);
            Serializator.serialize(deliveryService);
        }
    }

    /**
     *
     * @param pathName the name of the file
     * opens the window specified
     */
    private void open( String pathName) {
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
     * @param data data to inserted in the table
     */
    private void InitializeTable(ObservableList<MenuItem> data) {

        table2.setItems(data);

    }

    /**
     *
     * @param url
     * @param resourceBundle
     * initializes the interface
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
            table2.setItems(data);
        }

    }
}
