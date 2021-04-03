package udemy.ddd.ecommerce.customer;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    private String id;
    private Name name;
    private List<Address> addresses;
    private List<PhoneNumber> phoneNumbers;
    private List<EmailAddress> emailAddresses;
    private List<PaymentOption> paymentOptions;
}
