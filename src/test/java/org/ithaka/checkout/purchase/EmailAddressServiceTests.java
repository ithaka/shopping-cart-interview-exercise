package org.ithaka.checkout.purchase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailAddressServiceTests {
    @Autowired
    private EmailAddressService emailAddressService;

    @Before
    public void init() {
        emailAddressService.deleteAll();
        assertTrue(emailAddressService.findAll().isEmpty());
    }

    @Test
    public void should_be_able_to_persist_more_than_1_address() {
        EmailAddress emailAddress1 =
                emailAddressService.add(
                        new EmailAddress()
                                .setAddress("joe@example.com"));
        EmailAddress emailAddress2 =
                emailAddressService.add(
                        new EmailAddress()
                                .setAddress("jane@example.com"));

        Optional<EmailAddress> actual1 =
                emailAddressService.findById(emailAddress1.getId());
        Optional<EmailAddress> actual2 =
                emailAddressService.findById(emailAddress2.getId());

        assertEquals(emailAddress1, actual1.get());
        assertEquals(emailAddress2, actual2.get());
        assertNotEquals(emailAddress1, actual2.get());
    }
}
