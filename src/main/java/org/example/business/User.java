package org.example.business;

import org.example.data.UserType;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Sergiu
 */
public class User implements Serializable {
    /**
     * the id of the user
     */
    private String  id;
    /**
     * the username of the user
     */
    private String username;
    /**
     * the password of the user
     */
    private String password;
    /**
     *  the type of user
     */
    private UserType type;
    /**
     * the number of times a user has ordered
     */
    private int noOfTimes;
    /**
     * the total price of the user's orders
     */
    private int totalPrice;

    /**
     *
     * @param username username of the user
     * @param password password of the user
     * @param type the type of the user
     * it initializes the user
     */
    public User(String username, String password, UserType type) {
        this.id=UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.type = type;
        noOfTimes =0;
        totalPrice=0;
    }

    /**
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username sets the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return gets the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password sets the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return gets the type of the user
     */
    public UserType getType() {
        return type;
    }

    /**
     *
     * @param type sets the type of the user
     */
    public void setType(UserType type) {
        this.type = type;
    }

    /**
     *
     * @return the id of the user
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id id of the user
     * sets the id of the user
     *
     */


    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return gets noOfTimes
     */
    public int getNoOfTimes() {
        return noOfTimes;
    }

    /**
     *
     * @param noOfTimes the number of times a user has ordered
     * sets the number of times a user has ordered
     */
    public void setNoOfTimes(int noOfTimes) {
        this.noOfTimes = noOfTimes;
    }

    /**
     *
     * @return the total price of the user's orders
     */

    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     * @param totalPrice the total price of the user's orders
     *
     */

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @param o the object to be compared
     * @return true when the object o is of type user
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && type == user.type;
    }

    /**
     *
     * @return the hashCode of the user object
     */
    @Override
    public int hashCode() {
        return Objects.hash(username, type);
    }
}
