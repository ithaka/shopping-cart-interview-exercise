package org.ithaka.checkout.purchase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryAddressDao extends CrudRepository<DeliveryAddress, Long> {
    List<DeliveryAddress> findAll();
}
