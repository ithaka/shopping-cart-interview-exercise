package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Component
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
        //set this at the beginning to compare later
        order = setTimeOfOrder(order);
        //null check the values to make sure the order is complete before we post
        if(order.getDeliveryAddress() != null && order.getPaymentMethod() != null && order.getShoppingCart() != null){
            Optional<Order> previousOrder = orderDao.findById(orderDao.getMaxId());
            if(previousOrder.isPresent()) {
                Order possibleDuplicateOrder = previousOrder.get();
                long secondsBetween = (order.getOrderDate().getTime() - possibleDuplicateOrder.getOrderDate().getTime()) / 1000;
                if (!previousOrder.equals(order) && secondsBetween > 3) {
                    saveOrder(order);
                    return "Saved Successfully";
                }
                else{
                    return "Could not complete, Duplicate Transaction";
                }
            }
            else{
                saveOrder(order);
                return "Saved Successfully";
            }
        }
        else{
            return "Missing Values, Save Aborted";
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




