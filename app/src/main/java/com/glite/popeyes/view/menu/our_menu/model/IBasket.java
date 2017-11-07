package com.glite.popeyes.view.menu.our_menu.model;

import java.util.Collection;

/**
 * Created by PC on 10/10/2016.
 */
public interface IBasket {
    boolean addItem(OrderItem item);
    boolean removeItem(String itemId) throws ItemNotFoundException;
    Collection getCartDetails();
    OrderItem getItemFromCart(String itemId) throws ItemNotFoundException;
    int productCount();
    double getCartPrice();
    boolean addQuantityItem(int quantity, OrderItem item);
    boolean minusQuantityItem(int quantity, OrderItem item);
}
