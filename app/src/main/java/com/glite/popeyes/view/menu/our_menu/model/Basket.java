package com.glite.popeyes.view.menu.our_menu.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by PC on 10/10/2016.
 */
public class Basket implements IBasket {

    public String uid;
    private Map<String, OrderItem> map;

    public Basket() {
    }

    public Basket(String uid) {
        this.uid = uid;
        this.map = new HashMap<>();
    }

    @Override
    public boolean addItem(OrderItem item) {
        if (map.containsKey(item.getId())) {
            OrderItem item1 = map.get(item.getId());
            item1.setPrice(item1.getPrice() + item.getPrice());
            item1.setQuantity(item1.getQuantity() + item.getQuantity());
            return true;
        }
        map.put(item.getId(), item);
        return false;
    }

    @Override
    public boolean removeItem(String itemId) throws ItemNotFoundException {
        if (map.containsKey(itemId)) {
            map.remove(itemId);
            return true;
        } else throw new ItemNotFoundException(
                "Item with ID "+ itemId +" is not Found."
        );
    }

    @Override
    public boolean addQuantityItem(int quantity, OrderItem item) {
        if (map.containsKey(item.getId())) {
            OrderItem item1 = map.get(item.getId());
            item1.setQuantity(quantity);
            item1.setPrice(quantity * item1.getPrice());
            return true;
        }
        return false;
    }

    @Override
    public boolean minusQuantityItem(int quantity, OrderItem item) {
        if (map.containsKey(item.getId())) {
            OrderItem item1 = map.get(item.getId());
            item1.setQuantity(quantity);
            item1.setPrice(quantity * item1.getPrice());
            return true;
        }
        return false;
    }

    @Override
    public Collection getCartDetails() {
        return map.values();
    }

    @Override
    public OrderItem getItemFromCart(String itemId) throws ItemNotFoundException {
        if (map.containsKey(itemId)) {
            return map.get(itemId);
        } else throw new ItemNotFoundException(
                "Item with ID "+ itemId +" is not Found."
        );
    }

    @Override
    public int productCount() {
        return map.size();
    }

    @Override
    public double getCartPrice() {
        double price = 0.0d;
        Iterator<OrderItem> iterator = getCartDetails().iterator();

        while (iterator.hasNext()) {
            price += iterator.next().getPrice();
        }
        return price;
    }


}
