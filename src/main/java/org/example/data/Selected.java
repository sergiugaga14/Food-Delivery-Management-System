package org.example.data;

import org.example.business.MenuItem;
import org.example.business.User;
import org.example.presentation.EmployeeController;

/**
 * @author Sergiu
 */
public class Selected {
   /**
    * retains the userType of the login interface
    */
   public static UserType type;
   /**
    * retains the user that's currently using the interface
    */
   public static User user;
   /**
    * retains the id of the current client
    */
   public static String id;
   /**
    * retains the idem to be edited
    */
   public static MenuItem item;
   /**
    * retains the controller for the employee window
    */
   public static EmployeeController employeeController;

}
