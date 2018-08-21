package org.ithaka.checkout.purchase;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDao extends CrudRepository<Order, Long> {
    //get items with max id to compare previous orders
    @Query("SELECT coalesce(max(ch.id), 0) FROM Order ch")
    Long getMaxId();

    List<Order> findAll();

    //Returns most recent order, max id and user id
    Order findByIdAndUserId(Long id, Long userId);

    @Query("SELECT coalesce(max(ch.id), 0) FROM Order ch where ch.userId = :id")
    Order getMostRecentOrderByUserId(@Param("id") Long id);
}
