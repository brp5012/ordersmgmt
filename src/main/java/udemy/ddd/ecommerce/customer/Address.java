package udemy.ddd.ecommerce.customer;

import lombok.Data;

@Data
public class Address {
    private String address1;
    private String address2;
    private String address3;
    private String zipCode;
    private String city;
    private String state;
}
