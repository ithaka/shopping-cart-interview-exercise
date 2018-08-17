package org.ithaka.checkout.purchase;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Accessors(chain = true)
@Data
@Entity
public class DeliveryAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryAddress that = (DeliveryAddress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return street != null ? street.equals(that.street) : that.street == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        return result;
    }

    public DeliveryAddress setStreet(String street) {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setStreet(street);
        return deliveryAddress;
    }

    public Long getId() {
        return id;
    }
}
