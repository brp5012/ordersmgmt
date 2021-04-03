package udemy.ddd.ecommerce.fulfillment;

import lombok.Data;

@Data
public class Invoice {
    private String customerId;
    private String orderId;
    private String type;
}
