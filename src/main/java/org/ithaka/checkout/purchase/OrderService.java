package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {
    private DeliveryAddressService deliveryService;
    private PaymentMethodService paymentMethodService;
    private ShoppingCartService shoppingCartService;
    private OrderDao orderDao;

    public OrderService(DeliveryAddressService deliveryService,
                        PaymentMethodService paymentMethodService,
                        OrderDao orderDao,
                        ShoppingCartService shoppingCartService) {
        this.deliveryService = deliveryService;
        this.paymentMethodService = paymentMethodService;
        this.shoppingCartService = shoppingCartService;
        //injection by constructor added below
        this.orderDao = orderDao;
    }

    public Order findOne(long id) {
        Optional<Order> order = orderDao.findById(id);
        if(order.isPresent()){
            return order.get();
        }
        else{
            return null;
        }
    }

    public String postOrder(Order order) {
        //set this at the beginning to compare later to most recent transaction
        order = setTimeOfOrder(order);
        //null check the values to make sure the order is complete before we post
        if(order.getDeliveryAddress() == null && order.getPaymentMethod() == null && order.getShoppingCart() == null) {
            return "Missing Values, Save Aborted";
        }
        //return a message saying there are null items in the order and dont allow the post
        if(shoppingCartService.checkForNullItems(order)) {
            return "There are null items in the shopping cart";
        }

        Order previousOrder = orderDao.getMostRecentOrderByUserId(order.getUserId());
        if (previousOrder != null) {
            long secondsBetween = (order.getOrderDate().getTime() - previousOrder.getOrderDate().getTime()) / 1000;
            if (!previousOrder.equals(order) && secondsBetween > 3) {
                saveOrder(order);
                return "Saved Successfully";
            } else {
                return "Could not complete, Duplicate Transaction";
            }
        } else {
            saveOrder(order);
            return "Saved Successfully";
        }
    }

    public Iterable<Order> findAll() {
        return orderDao.findAll();
    }

    public void saveOrder(Order order){
        shoppingCartService.add(order.getShoppingCart());
        paymentMethodService.add(order.getPaymentMethod());
        deliveryService.add(order.getDeliveryAddress());
        orderDao.save(order);
    }

    public Order setTimeOfOrder(Order order) {
        Date date = new Date();
        long time = date.getTime();
        new Timestamp(time);
        order.setOrderDate(new Timestamp(time));
        return order;
    }
}




