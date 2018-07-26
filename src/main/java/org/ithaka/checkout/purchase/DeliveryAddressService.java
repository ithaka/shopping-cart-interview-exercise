package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeliveryAddressService {
    private DeliveryAddressDao deliveryAddressDao;

    public DeliveryAddressService(DeliveryAddressDao deliveryAddressDao) {
        this.deliveryAddressDao = deliveryAddressDao;
    }

    public DeliveryAddress add(DeliveryAddress deliveryAddress) {
        return deliveryAddressDao.save(deliveryAddress);
    }

    public List<DeliveryAddress> findAll() {
        return deliveryAddressDao.findAll();
    }

    public Optional<DeliveryAddress> findById(Long id) {
        return deliveryAddressDao.findById(id);
    }

    public void deleteAll() {
        deliveryAddressDao.deleteAll();
    }
}
