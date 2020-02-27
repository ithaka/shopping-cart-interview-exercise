package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderService {
    private EmailAddressService emailAddressService;
    private OrderDao orderDao;
    private PaymentMethodService paymentMethodService;
    private ShoppingCartService shoppingCartService;

    public OrderService(EmailAddressService emailAddressService,
                        OrderDao orderDao,
                        PaymentMethodService paymentMethodService,
                        ShoppingCartService shoppingCartService) {
        this.emailAddressService = emailAddressService;
        this.paymentMethodService = paymentMethodService;
        this.shoppingCartService = shoppingCartService;
    }

    public Optional<Order> findOne(long id) {
        //TODO Complete this
        return Optional.empty();
    }

    public Order postOrder(Order order) {
        //TODO Complete this
        return order;
    }
}
