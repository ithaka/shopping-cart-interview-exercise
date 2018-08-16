package org.ithaka.checkout;

import org.ithaka.checkout.purchase.Order;
import org.ithaka.checkout.purchase.OrderService;
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
        //TODO Complete this
        // What would you expect a GET to do?
        return Optional.empty();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Order post(@RequestBody Order order) {
        // What would you expect a POST to do?
        return null;
    }
}
