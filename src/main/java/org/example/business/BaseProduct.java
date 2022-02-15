package org.example.business;

/**
 * @author Sergiu
 *
 */
public class BaseProduct extends MenuItem{
    /**
     *
     * @param title the title of the base product
     * @param rating the rating of the base product
     * @param calories the calories of the base product
     * @param protein the protein of the base product
     * @param fat the fat of the base product
     * @param sodium the sodium of the base product
     * @param price the price of the base product
     *  initializes the base product with the values transmitted as parameters
     */
    public BaseProduct(String title, double rating, int calories, int protein, int fat, int sodium, int price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    /**
     *
     * @return the price of a base product
     */
    public int computePrice()
    {
        return getPrice();
    }
}
