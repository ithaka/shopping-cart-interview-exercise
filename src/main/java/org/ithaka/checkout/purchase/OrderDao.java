package org.ithaka.checkout.purchase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Long> {
    List<Order> findAll();
}
