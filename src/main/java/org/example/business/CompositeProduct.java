package org.example.business;

import java.util.ArrayList;

/**
 * @author Sergiu
 */
public class CompositeProduct extends MenuItem{

    private ArrayList<MenuItem> compositeProduct;

    /**
     *
     * @param title the title of the composite product
     * @param compositeProduct the list of items from which the product is composed
     *  initializes the value of the composite product
     */
    public CompositeProduct(String title,ArrayList<MenuItem> compositeProduct) {
        this.title=title;
        this.rating=0;
        this.calories=0;
        this.fat=0;
        this.protein=0;
        this.sodium=0;
        this.compositeProduct=compositeProduct;
        for(MenuItem item: compositeProduct)
        {

            this.rating+=item.getRating();
            this.sodium+=item.getSodium();
            this.fat+=item.getFat();
            this.protein+=item.getProtein();
            this.calories+=item.getCalories();

        }
       this.rating/= compositeProduct.size();
        this.price=computePrice();
    }

    /**
     *
     * @return the price of the composite product
     */
    @Override
    public int computePrice() {
        int price=0;
        for(MenuItem prod: compositeProduct)
        {
            price+=prod.computePrice();
        }
        return price;
    }

    /**
     *
     * @return the list of items from the composite product
     */
    public ArrayList<MenuItem> getCompositeProduct() {
        return compositeProduct;
    }

    /**
     *
     * @param compositeProduct the list of items to be set
     * it sets the list of items of the composite product with the value transmitted as parameter
     */

    public void setCompositeProduct(ArrayList<MenuItem> compositeProduct) {
        this.compositeProduct = compositeProduct;
    }
}
