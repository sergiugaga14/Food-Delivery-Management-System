package org.example.business;

import org.example.data.Selected;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Sergiu
 */
public class Order implements Serializable {
    /**
     * the id of the order
     */
    private String orderId;
    /**
     * the id of the client
     */
    private String clientId;
    /**
     * the date of the order
     */
    private Calendar date;
    /**
     * the totalPrice of the order
     */
    private int totalPrice;

    /**
     *
     * @param totalPrice total price of the order
     * initializes the order
     */
    public Order(int totalPrice) {
        this.orderId = UUID.randomUUID().toString();
        this.clientId = Selected.id;
        this.date=Calendar.getInstance();
        this.totalPrice=totalPrice;
    }

    /**
     *
     * @return the total price of the order
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     * @param totalPrice the total price of the order
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     *
     * @return the id of the order
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId the id of the order
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return the id of the client
     */
    public String getClientId() {
        return clientId;
    }

    /**
     *
     * @param clientId the id of the client
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     *
     * @return the date of the order
     */
    public Calendar getDate() {
        return date;
    }

    /**
     *
     * @param date the date of the order
     */
    public void setDate(Calendar date) {
        this.date = date;
    }

    /**
     *
     * @param o object to be compared
     * @return true if two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderId == order.orderId && clientId == order.clientId && Objects.equals(date, order.date);
    }

    /**
     *
     * @return the hashcode of the order
     */
    @Override
    public int hashCode() {
        return Objects.hash(orderId, clientId, date);
    }
}
