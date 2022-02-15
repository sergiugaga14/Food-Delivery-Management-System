package org.example.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
/**
 * @author Sergiu
 *
 */
public interface IDeliveryServiceProcessing {
   /**
    *
    * @param item the item to be added to the menu
    * @pre item != null
    * @pre myMenu != null
    * @post myMenu.contains(item)
    */
   public  void createBaseProd(BaseProduct item);

   /**
    *
    * @param item the item to be added to the menu
    * @pre item != null
    * @pre myMenu != null
    * @post myMenu.contains(item)
    */
   public void createCompoProd(CompositeProduct item);

   /**
    *
    * @param prod the item to be deleted
    * @pre item!=null the item should not be null
    * @post !myMenu.contains(item) , the menu of items should not contain the item anymore
    */
   public void deleteMenuItem(MenuItem prod);

   /**
    *
    * @param item the item to be edited
    * @param title the new title
    * @param rating the new rating
    * @param calories the new value of calories
    * @param protein the new value for proteins
    * @param fat the new value for fat
    * @param sodium the new value for sodium
    * @param price the new value for price
    * @pre item!=null
    * @pre title!=null
    * @pre rating!= null
    * @pre calories!=null
    * @pre protein!=null
    * @pre fat!=null
    * @pre sodium!= null
    * @pre price!=null
    * edits a base product
    */
   public void editBaseProduct(BaseProduct item, String title, Double rating, Integer calories,Integer protein, Integer fat,Integer sodium,Integer price);

   /**
    *
    * @param item item to be edited
    * @param title new title
    * @param items new list of items
    * @pre item!=null
    * @pre title!=null
    * @pre items!=null
    * edits a composite product
    */
   public void editCompoProduct(CompositeProduct item,String title, ArrayList<MenuItem> items);

   /**
    *
    * @param menu the list of items obtained from the file
    * @pre menu!=null
    * @post myMenu!=null
    */
   public void importMenu(LinkedHashSet<MenuItem> menu);

   /**
    *
    * @param start start hour
    * @param stop stop hour
    * @return the orders made between the 2 hours
    * @pre start >= 0 and  24 >= start and stop >= 0 and  24 >= stop and stop > start
    */

   public ArrayList<Order> generateReport1(int start,int stop);

   /**
    *
    * @param noOfTimes number of times a product was ordered
    * @return the products that have been ordered at least the number of times specified
    * @pre noOfTimes > 0
    */

   public HashMap<MenuItem, Long> generateReport2(int noOfTimes);

   /**
    *
    * @param noOfTimes number of times a client ordered
    * @param value value specified for the total price of the orders
    * @return the clients that ordered at least noOfTimes and the total price of their orders are at least the value
    * @pre  noOfTimes>0 and value>0
    */

   public ArrayList<User> generateReport3(int noOfTimes, int value);

   /**
    *
    * @param day the day of the order
    * @param month the month of the order
    * @param year the year of the order
    * @return the products ordered and the number of times they were ordered at the specified date
    * @pre day > 0 and 31 >= day and month > 0 and  12>= month and year > 0
    */
   public Map<MenuItem,Long> generateReport4(int day,int month,int year);

   /**
    *
    * @param items list of items
    * @pre items!=null
    * @pre wellFormed()
    * @post listOfOrders!=null
    * @post wellFormed()
    * creates an order
    */

   public void createOrder(ArrayList<MenuItem> items);

   /**
    *
    * @param title title filter
    * @param rating rating filter
    * @param calories calories filter
    * @param protein protein filter
    * @param fat fat filter
    * @param sodium sodium filter
    * @param price price filter
    * @return the items that were filtered
    * @pre myMenu!=null
    */
   public LinkedHashSet<MenuItem> searchItem(String title, String rating,String calories, String protein, String fat, String sodium, String price);

   /**
    * @pre listOfOrders!=null
    */
   public void generateBill();

}
