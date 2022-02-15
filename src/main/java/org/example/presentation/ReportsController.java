package org.example.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.business.DeliveryService;
import org.example.business.MenuItem;
import org.example.business.Order;
import org.example.data.Serializator;
import org.example.business.User;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Sergiu
 */
public class ReportsController implements Initializable {
    /**
     * start hour text field
     */
    @FXML
    private TextField start;
    /**
     *  stop hour text field
     */
    @FXML
    private TextField stop;
    /**
     *  generate report 1 button
     */
    @FXML
    private Button generate1;
    /**
     * number of times text field for report 2
     */
    @FXML
    private TextField number2;
    /**
     * generate report 2 button
     */
    @FXML
    private Button generate2;
    /**
     * number of times text field for report 3
     */
    @FXML
    private TextField number3;
    /**
     * value text field
     */
    @FXML
    private TextField value;
    /**
     *generate report 3 button
     */
    @FXML
    private Button generate3;
    /**
     * generate report 4 button
     */
    @FXML
    private Button generate4;
    /**
     * day text field
     */
    @FXML
    private TextField day;
    /**
     * month text field
     */
    @FXML
    private TextField month;
    /**
     * year text field
     */
    @FXML
    private TextField year;
    /**
     * text area
     */
    @FXML
    private TextArea textArea;

    /**
     *
     * @param e event from the interface
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void handleButtons(ActionEvent e) throws IOException, ClassNotFoundException {
       if(e.getSource()==generate1)
       {
           DeliveryService deliveryService= Serializator.deserialize();
           int str= Integer.valueOf(start.getText());
           int stp=Integer.valueOf(stop.getText());
           ArrayList<Order> listOfOrders= deliveryService.generateReport1(str,stp);
           textArea.setText("");
           SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
           for(Order o: listOfOrders)
           {
             textArea.appendText("OrderId: "+o.getOrderId()+" Date: "+formatter.format(o.getDate().getTime())+"\n");
           }
       }
        if(e.getSource()==generate2)
        {
          int no=Integer.valueOf(number2.getText());
          DeliveryService deliveryService= Serializator.deserialize();
          HashMap<MenuItem,Long> list2=deliveryService.generateReport2(no);
          textArea.setText("");
          for(MenuItem m: list2.keySet())
          {
              textArea.appendText(m.toString()+" X "+list2.get(m)+"\n");
          }

        }
        if(e.getSource()==generate3)
        {
            int no=Integer.valueOf(number3.getText());
            int val=Integer.valueOf(value.getText());
          DeliveryService deliveryService=Serializator.deserialize();
          ArrayList<User> list= deliveryService.generateReport3(no,val);
            textArea.setText("");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            for(User u: list)
            {
                textArea.appendText("User name: "+u.getUsername()+" No of times:"+u.getNoOfTimes()+" Total: "+u.getTotalPrice()+"\n");
            }
        }

        if(e.getSource()==generate4) {
            int d=Integer.valueOf(day.getText());
            int m=Integer.valueOf(month.getText());
            int y=Integer.valueOf(year.getText());
            DeliveryService deliveryService=Serializator.deserialize();
           Map<MenuItem,Long> list1= deliveryService.generateReport4(d,m,y);
            textArea.setText("");
            for(MenuItem it: list1.keySet())
            {
                textArea.appendText(it.toString()+" X "+list1.get(it)+"\n");
            }
        }
    }

    /**
     *
     * @param list list of menu items
     * @return the total price of the items from the list
     */
   public int computePrice(ArrayList<MenuItem> list)
   {
       int price=0;
       for(MenuItem m: list)
           price+=m.computePrice();
       return price;
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
