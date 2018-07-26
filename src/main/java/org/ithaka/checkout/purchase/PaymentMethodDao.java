package org.ithaka.checkout.purchase;

import org.springframework.data.repository.CrudRepository;

public interface PaymentMethodDao extends CrudRepository<PaymentMethod, Long> {
}
