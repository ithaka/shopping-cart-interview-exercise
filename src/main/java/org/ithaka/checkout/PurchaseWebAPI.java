package org.ithaka.checkout;

import org.ithaka.checkout.purchase.Order;
import org.ithaka.checkout.purchase.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order", consumes = "application/json", produces = "application/json")
public class PurchaseWebAPI {
    private OrderService orderService;

    public PurchaseWebAPI(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Order getOrder(@PathVariable Long id) {
        return orderService.findOne(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Order> getOrders() {
        return orderService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postOrder(@RequestBody Order order) {
        String missingValues = orderService.postOrder(order);
        return missingValues;
    }
}
