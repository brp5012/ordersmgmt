package udemy.ddd.ecommerce.billing;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Invoice {
    private String customerId;
    private String orderId;
    private BigDecimal totalCost;
}
