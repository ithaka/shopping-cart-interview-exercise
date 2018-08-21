package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartService(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    public ShoppingCart add(ShoppingCart shoppingCart) {
         return shoppingCartDao.save(shoppingCart);
    }

    //method to null check that the shopping cart contains all needed items for the transaction
    public Boolean checkForNullItems(Order order){
        if (order.getShoppingCart().getItem() == null){
            return true;
        }
        else if (order.getShoppingCart().getPrice() == null){
            return true;
        }
        else if (order.getShoppingCart().getQuantity() == null){
            return true;
        }
        return false;
    }
}
