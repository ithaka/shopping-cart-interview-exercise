package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

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
    }

    public Optional<Order> findOne(long id) {
        return Optional.empty();
    }

    public Order postOrder(Order order) {
        return order;
    }
}
