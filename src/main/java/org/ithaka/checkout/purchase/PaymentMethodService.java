package org.ithaka.checkout.purchase;

import org.springframework.stereotype.Component;

@Component
public class PaymentMethodService {
    private PaymentMethodDao paymentMethodDao;

    public PaymentMethodService(PaymentMethodDao paymentMethodDao) {
        this.paymentMethodDao = paymentMethodDao;
    }

    public PaymentMethod createPayment(PaymentMethod paymentMethod) {
        return paymentMethodDao.save(paymentMethod);
    }

    public PaymentMethod add(PaymentMethod paymentMethod) {
        return paymentMethodDao.save(paymentMethod);
    }
}
