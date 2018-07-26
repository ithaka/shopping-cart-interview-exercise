package org.ithaka.checkout.purchase;

import org.springframework.data.repository.CrudRepository;

public interface ShoppingCartDao extends CrudRepository<ShoppingCart, Long> {
}
