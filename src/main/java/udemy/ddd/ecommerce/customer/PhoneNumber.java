package udemy.ddd.ecommerce.customer;

import lombok.Data;

@Data
public class PhoneNumber {
    private String phoneNumber;
    private String extension;
    private String formattedPhoneNumber;
    private String phoneType;
}
