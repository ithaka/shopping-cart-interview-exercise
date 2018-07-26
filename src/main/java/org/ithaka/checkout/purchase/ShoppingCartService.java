package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCartService {
    private ShoppingCartDao shoppingCartDao;

    public ShoppingCartService(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    public void foo() {
        System.out.println("hello from ShoppingCartService");
    }
}
