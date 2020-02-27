package org.ithaka.checkout.purchase;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailAddressDao extends CrudRepository<EmailAddress, Long> {
    List<EmailAddress> findAll();
}
