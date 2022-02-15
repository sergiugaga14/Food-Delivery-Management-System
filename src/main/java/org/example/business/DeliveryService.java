package org.example.business;
import org.example.data.FileOperations;
import org.example.data.Selected;
import org.example.data.UserType;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Sergiu
 * @invariant  wellFormed()
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {
    /**
     * the map that contains the orders and the items associated with the order
     */
    private HashMap<Order, ArrayList<MenuItem>> listOfOrders = new HashMap<>();
    /**
     * the set that contains the items of the menu
     */
    private LinkedHashSet<MenuItem> myMenu = new LinkedHashSet<>();
    /**
     * the set that contains the users
     */
    private LinkedHashSet<User> listOfUsers;

    /**
     *
     * @return the list of Orders
     */

    public HashMap<Order, ArrayList<MenuItem>> getListOfOrders() {
        return listOfOrders;
    }

    /**
     *
     * @param listOfOrders list of orders
     */

    public void setListOfOrders(HashMap<Order, ArrayList<MenuItem>> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    /**
     *
     * @return the menu
     */
    public LinkedHashSet<MenuItem> getMyMenu() {
        return myMenu;
    }

    /**
     *
     * @param myMenu the menu of items
     */
    public void setMyMenu(LinkedHashSet<MenuItem> myMenu) {
        this.myMenu = myMenu;
    }

    /**
     *
     * @return the list of users
     */
    public LinkedHashSet<User> getListOfUsers() {
        return listOfUsers;
    }

    /**
     *
     * @param listOfUsers list of users
     */
    public void setListOfUsers(LinkedHashSet<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    /**
     *
     * @return true if the deliveryService object is well formed
     * that means that the total price of the orders should be equal with the total price of the items from contained in the orders
     */
   public boolean wellFormed()
   {
       boolean bool;
       int totalPrice1=0;
       int totalPrice2=0;
       for(Order o: listOfOrders.keySet())
       {   totalPrice1+=o.getTotalPrice();
           for(MenuItem m: listOfOrders.get(o))
           {
               totalPrice2+=m.computePrice();
           }
       }
       bool=totalPrice2==totalPrice1;
       return bool;
   }

    /**
     *
     * @param item the item to be added to the menu
     */
    @Override
    public void createBaseProd(BaseProduct item) {
     assert item!=null;
     assert myMenu!=null;
     myMenu.add(item);
     assert myMenu.contains(item);
    }

    /**
     *
     * @param item the item to be added to the menu
     */
    @Override
    public void createCompoProd(CompositeProduct item) {
        assert item!=null;
        assert myMenu!=null;
        myMenu.add(item);
        assert myMenu.contains(item);
    }

    /**
     *
     * @param prod the item to be deleted
     */
    @Override
    public void deleteMenuItem(MenuItem prod) {
        assert prod != null;
        assert myMenu.contains(prod);
        myMenu.remove(prod);
        assert !myMenu.contains(prod);
    }
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
     */
    @Override
    public void editBaseProduct(BaseProduct item, String title, Double rating, Integer calories, Integer protein, Integer fat, Integer sodium, Integer price) {
        assert item != null;
        assert myMenu != null;
        assert myMenu.contains(item);
        assert title != null && !title.isBlank();
        assert rating != null;
        assert calories != null;
        assert protein != null;
        assert fat != null;
        assert sodium != null;
        assert price != null;
        for (MenuItem m : myMenu) {
            if (m.equals(item)) {
                m.setTitle(title);
                m.setRating(rating);
                m.setCalories(calories);
                m.setProtein(protein);
                m.setFat(fat);
                m.setSodium(sodium);
                m.setPrice(price);
                break;
            }
        }


    }

    /**
     *
     * @param item item to be edited
     * @param title new title
     * @param items new list of items
     */
    @Override
    public void editCompoProduct(CompositeProduct item, String title, ArrayList<MenuItem> items) {
        assert item != null;
        assert myMenu.contains(item);
        assert title != null;
        assert items != null;
        for (MenuItem m : myMenu) {
            if (m.getClass().getSimpleName().equals("CompositeProduct")) {
                CompositeProduct c = (CompositeProduct) m;
                if (c.equals(item)) {
                    c.setTitle(title);
                    c.setCompositeProduct(items);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param menu the list of items obtained from the file
     */
    @Override
    public void importMenu(LinkedHashSet<MenuItem> menu) {
        assert menu != null;
        setMyMenu(menu);
        assert myMenu != null;
    }

    /**
     *
     * @param start start hour
     * @param stop stop hour
     * @return  the orders made between start and stop hour
     */
    @Override
    public ArrayList<Order> generateReport1(int start, int stop) {
        assert start >= 0 && start <= 24 && stop >= 0 && stop <= 24 && start < stop;
        ArrayList<Order> listy = (ArrayList<Order>) getListOfOrders().keySet().stream()
                .filter(o -> o.getDate().get(Calendar.HOUR_OF_DAY) >= start && o.getDate().get(Calendar.HOUR_OF_DAY) <= stop)
                .collect(Collectors.toList());
        return listy;
    }

    /**
     *
     * @param noOfTimes number of times a product was ordered
     * @return the products that were ordered at least noOfTimes times
     */
    @Override
    public HashMap<MenuItem, Long> generateReport2(int noOfTimes) {
        assert noOfTimes > 0;
        ArrayList<MenuItem> list1 = new ArrayList<>();
        for (ArrayList<MenuItem> o : getListOfOrders().values()) {
            list1.addAll(o);
        }
        Map<MenuItem, Long> items = list1.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        ArrayList<MenuItem> list2 = (ArrayList<MenuItem>) items.keySet().stream()
                .filter(c -> items.get(c) >= noOfTimes)
                .collect(Collectors.toList());
        HashMap<MenuItem, Long> items2 = new HashMap<>();
        for (MenuItem m : list2) items2.put(m, items.get(m));
        return items2;
    }

    /**
     *
     * @param noOfTimes number of times a client ordered
     * @param value value specified for the total price of the orders
     * @return the clients that have ordered at least noOfTimes times and whose orders' value is greater or equal to value
     */
    @Override
    public ArrayList<User> generateReport3(int noOfTimes, int value) {
        assert noOfTimes > 0 && value > 0;
        ArrayList<User> list = (ArrayList<User>) getListOfUsers().stream()
                .filter(u -> u.getType().equals(UserType.CLIENT))
                .filter(u -> u.getNoOfTimes() >= noOfTimes)
                .filter(u -> u.getTotalPrice() >= value)
                .collect(Collectors.toList());
        return list;
    }

    /**
     *
     * @param day the day of the order
     * @param month the month of the order
     * @param year the year of the order
     * @return the products and the number of time they were ordered in the specified day
     */
    @Override
    public Map<MenuItem, Long> generateReport4(int day, int month, int year) {
        assert day > 0 && day <= 31 && month > 0 && month <= 12 && year > 0;
        ArrayList<Order> orders = (ArrayList<Order>) getListOfOrders().keySet().stream()
                .filter(o -> o.getDate().get(Calendar.DAY_OF_MONTH) == day && o.getDate().get(Calendar.MONTH) == month - 1 && o.getDate().get(Calendar.YEAR) == year)
                .collect(Collectors.toList());
        ArrayList<MenuItem> items = new ArrayList<>();
        for (Order o : orders) {
            items.addAll(getListOfOrders().get(o));
        }
        Map<MenuItem, Long> list1 = items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return list1;
    }

    /**
     *
     * @param items list of items
     */
    @Override
    public void createOrder(ArrayList<MenuItem> items) {
        assert items != null;
        assert wellFormed();
        Integer sum = items.stream()
                .map(x -> x.getPrice())
                .reduce(0, Integer::sum);
        Order order = new Order(sum);
        listOfOrders.put(order, items);
        String s = "OrderId: " + order.getOrderId() + "\n";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (User u : listOfUsers) {
            if (u.equals(Selected.user)) {
                u.setNoOfTimes(u.getNoOfTimes() + 1);
                u.setTotalPrice(u.getTotalPrice() + sum);
            }
        }
        s += "Date: " + formatter.format(order.getDate().getTime()) + "\n";
        for (MenuItem m : items)
            s += m.toString() + "\n";

        setChanged();
        notifyObservers(s);
        assert wellFormed();
        assert listOfOrders != null;
    }
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
     */
    @Override
    public LinkedHashSet<MenuItem> searchItem(String title, String rating, String calories, String protein, String fat, String sodium, String price) {
        assert myMenu != null;
        LinkedHashSet<MenuItem> list = (LinkedHashSet<MenuItem>) myMenu.stream()
                .filter(s -> { if (!title.isBlank()) { return s.getTitle().toLowerCase().contains(title.toLowerCase()); }return true; })
                .filter(s -> { if (isDouble(rating)) { return s.getRating() == Double.valueOf(rating); }return true;})
                .filter(s -> { if (isInteger(calories)) { return s.getCalories() == Integer.valueOf(calories); }return true; })
                .filter(s -> { if (isInteger(protein)) { return s.getProtein() == Integer.valueOf(protein); }return true; })
                .filter(s -> { if (isInteger(fat)) { return s.getFat() == Integer.valueOf(fat); }return true; })
                .filter(s -> { if (isInteger(sodium)) { return s.getSodium() == Integer.valueOf(sodium); }return true; })
                .filter(s -> { if (isInteger(price)) { return s.getPrice() == Integer.valueOf(price); }return true; })
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return list;
    }

    /**
     *
     * @param str a string that contains a double number
     * @return true if the number inside the string is a valid double
     */
    boolean isDouble(String str) {
        try {
            if (str.isBlank()) return false;
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     * @param str a string that contains a integer number
     * @return true if the number inside the string is a valid integer
     */
    boolean isInteger(String str) {
        try {
            if (str.isBlank()) return false;
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     *
     */
    @Override
    public void generateBill() {
        assert listOfOrders != null;
        for (Order ord : listOfOrders.keySet()) {
            generateBillContent(ord, listOfOrders.get(ord));
        }
    }

    /**
     *
     * @param ord details of the order
     * @param items items of the order
     * @pre ord!=null
     * @pre items!=null
     * @pre listOfUsers!=null
     */
    public void generateBillContent(Order ord, ArrayList<MenuItem> items) {
        assert ord != null;
        assert items != null;
        assert listOfUsers != null;
        String path = "Bill" + ord.getOrderId() + ".txt";
        FileOperations.createFile(path);
        String content = "";
        User user = null;
        int price = 0;
        for (User u : listOfUsers) {
            if (ord.getClientId().equals(u.getId()))
                user = u;
        }
        content = content + "Buyer: " + user.getUsername() + "\n";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        content += "Date: " + formatter.format(ord.getDate().getTime()) + "\n";
        for (MenuItem it : items) {
            content = content + "Item: " + it.getTitle() + "\n";
            price += it.computePrice();
        }
        content = content + "Total Price: " + price + "\n";
        FileOperations.writeFile(path, content);
    }
}
