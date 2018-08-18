package org.ithaka.checkout.purchase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Long> {
    //get items with max id to compare previous orders
    @Query("SELECT coalesce(max(ch.id), 0) FROM Order ch")
    Long getMaxId();

    List<Order> findAll();
}
