package udemy.ddd.ecommerce.order.promotion;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Promotion {
    private String id;
    private BigDecimal value;
}
