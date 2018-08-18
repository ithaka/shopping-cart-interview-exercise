package org.ithaka.checkout.purchase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Before
    public void init() {
        //parent needs to be deleted first before a child gets removed, this now will complete 2 tests
        orderDao.deleteAll();
        deliveryAddressService.deleteAll();
        assertTrue(deliveryAddressService.findAll().isEmpty());
        assertTrue(orderDao.findAll().isEmpty());
    }

    @Test
    public void should_be_able_to_have_2_addresses() {
        //lets create a new instance of delivery address instead
        DeliveryAddress delivery1 = new DeliveryAddress();
        delivery1.setStreet("1 main st");
        DeliveryAddress deliveryAddress1 = deliveryAddressService.add(delivery1);
        DeliveryAddress delivery2 = new DeliveryAddress();
        delivery2.setStreet("2 main st");
        DeliveryAddress deliveryAddress2 = deliveryAddressService.add(delivery2);

        Optional<DeliveryAddress> actual1 =
                deliveryAddressService.findById(deliveryAddress1.getId());
        Optional<DeliveryAddress> actual2 =
                deliveryAddressService.findById(deliveryAddress2.getId());

        assertEquals(deliveryAddress1, actual1.get());
        assertEquals(deliveryAddress2, actual2.get());
        assertNotEquals(deliveryAddress1, actual2.get());
    }

    @Test
    //not sure why h2 is starting my inserts at 4
    public void should_get_max() {
        Order order = createTestOrder();
        orderService.saveOrder(order);
        long id = orderDao.getMaxId();
        assertEquals(id, 4);
    }

    @Test
    public void should_get_seconds_between() {
        Date date = new Date();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date date2 = new Date();
        long secondsBetween = (date.getTime() - date2.getTime()) / 1000;
        assertEquals(secondsBetween, -5);
    }

    @Test
    public void should_save_order() {
        Order order = createTestOrder();
        shoppingCartService.add(order.getShoppingCart());
        paymentMethodService.add(order.getPaymentMethod());
        deliveryAddressService.add(order.getDeliveryAddress());
        Order savedOrder = orderDao.save(order);
        assertEquals(savedOrder, order);
    }

    @Test
    public void should_set_time() {
        Order order = createTestOrder();
        Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        order.setOrderDate(timestamp);
        assertEquals(order.getOrderDate(), timestamp);
    }

    public Order createTestOrder(){
        Order order = new Order();
        DeliveryAddress address = new DeliveryAddress();
        address.setStreet("1 main st");
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setMethod("visa");
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setItem("cherrios");
        shoppingCart.setPrice(new Float(2));
        shoppingCart.setQuantity(2);
        order.setPaymentMethod(paymentMethod);
        order.setShoppingCart(shoppingCart);
        order.setDeliveryAddress(address);
        return order;
    }
}
