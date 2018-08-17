package org.ithaka.checkout.purchase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeliveryAddressServiceTests {
    @Autowired
    private DeliveryAddressService deliveryAddressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Before
    public void init() {
        deliveryAddressService.deleteAll();
        assertTrue(deliveryAddressService.findAll().isEmpty());
    }

    @Test
    public void should_be_able_to_have_2_addresses() {
        DeliveryAddress deliveryAddress1 =
                deliveryAddressService.add(new DeliveryAddress().setStreet("1 main st"));
        DeliveryAddress deliveryAddress2 =
                deliveryAddressService.add(
                        new DeliveryAddress()
                                .setStreet("2 main st"));

        Optional<DeliveryAddress> actual1 =
                deliveryAddressService.findById(deliveryAddress1.getId());
        Optional<DeliveryAddress> actual2 =
                deliveryAddressService.findById(deliveryAddress2.getId());

        assertEquals(deliveryAddress1, actual1.get());
        assertEquals(deliveryAddress2, actual2.get());
        assertNotEquals(deliveryAddress1, actual2.get());
    }

    @Test
    public void should_get_max() {
        Order order = createTestOrder();
        orderService.saveOrder(order);
        long id = orderDao.getMaxId();
        assertEquals(id, 0);
    }

    @Test
    public void should_save_order() {
        Order order = createTestOrder();
        orderService.saveOrder(order);
    }

    public Order createTestOrder(){
        Order order = new Order();
        order.setDeliveryAddress(new DeliveryAddress().setStreet("1 main st"));
        order.setPaymentMethod(new PaymentMethod("visa"));
        ShoppingCart cart = new ShoppingCart();
        cart.setItem("cherrios");
        cart.setPrice(new Float(2));
        cart.setQuantity(2);
        order.setShoppingCart(cart);
        return order;
    }
}
