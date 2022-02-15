package org.example.business;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author Sergiu
 */
public abstract class MenuItem implements Serializable {
    /**
     * the title of the item
     */
    protected String title;
    /**
     * the rating of the item
     */
    protected double rating;
    /**
     * the calories of the item
     */
    protected int calories;
    /**
     * the protein of the item
     */

    protected int protein;
    /**
     * the fat of the item
     */
    protected int fat;
    /**
     * the sodium of the item
     */
    protected int sodium;
    /**
     * the price of the item
     */
    protected int price;

    /**
     *
     * @return the price of the item
     */
   public abstract int computePrice();

    /**
     *
     * @return the title of the item
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title title to be set
     *  it sets the title of the item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the rating og the item
     */

    public double getRating() {
        return rating;
    }

    /**
     *
     * @param rating the rating of the item
     */

    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     *
     * @return the calories of the item
     */
    public int getCalories() {
        return calories;
    }

    /**
     *
     * @param calories the calories of the item
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

    /**
     *
     * @return the protein of the item
     */
    public int getProtein() {
        return protein;
    }

    /**
     *
     * @param protein the protein of the item
     */
    public void setProtein(int protein) {
        this.protein = protein;
    }

    /**
     *
     * @return the fat of the item
     */
    public int getFat() {
        return fat;
    }

    /**
     *
     * @param fat the fat of the item
     */
    public void setFat(int fat) {
        this.fat = fat;
    }

    /**
     *
     * @return the sodium of the item
     */
    public int getSodium() {
        return sodium;
    }

    /**
     *
     * @param sodium the sodium of the item
     */
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    /**
     *
     * @return the price of the item
     */
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price the price of the item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     *
     * @return the string form of the item
     */
    @Override
    public String toString() {
        return
                 title +
                ", " + rating+
                ", " + calories +
                ", " + protein +
                ", " + fat +
                ", " + sodium +
                ", " + price;
    }

    /**
     *
     * @param o the object to be compared
     * @return true when two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(title, menuItem.title);
    }

    /**
     *
     * @return the hashCode of the item
     */
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
