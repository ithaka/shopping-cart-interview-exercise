package org.ithaka.checkout.purchase;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Accessors(chain = true)
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private DeliveryAddress deliveryAddress;
    @OneToOne
    private PaymentMethod paymentMethod;
    @OneToOne
    private ShoppingCart shoppingCart;
}
