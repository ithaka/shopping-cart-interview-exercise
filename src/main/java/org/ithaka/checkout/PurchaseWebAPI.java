package org.ithaka.checkout;

import org.ithaka.checkout.purchase.DeliveryAddress;
import org.ithaka.checkout.purchase.Order;
import org.ithaka.checkout.purchase.OrderService;
import org.ithaka.checkout.purchase.PaymentMethod;
import org.ithaka.checkout.purchase.ShoppingCart;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/", consumes = "application/json", produces = "application/json")
public class PurchaseWebAPI {
    private OrderService orderService;

    public PurchaseWebAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Optional<Order> get() {
        return Optional.of(new Order()
                .setDeliveryAddress(new DeliveryAddress()
                        .setStreet("main"))
                .setPaymentMethod(new PaymentMethod()
                        .setMethod("visa"))
                .setShoppingCart(new ShoppingCart()
                        .setItem("coffee")));
        //return orderService.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Order post(@RequestBody Order order) {
        return orderService.postOrder(order);
    }
}
