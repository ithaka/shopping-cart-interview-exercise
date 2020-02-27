package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmailAddressService {
    private EmailAddressDao emailAddressDao;

    public EmailAddressService(EmailAddressDao emailAddressDao) {
        this.emailAddressDao = emailAddressDao;
    }

    public EmailAddress add(EmailAddress emailAddress) {
        return emailAddressDao.save(emailAddress);
    }

    public List<EmailAddress> findAll() {
        return emailAddressDao.findAll();
    }

    public Optional<EmailAddress> findById(Long id) {
        return emailAddressDao.findById(id);
    }

    public void deleteAll() {
        emailAddressDao.deleteAll();
    }
}
