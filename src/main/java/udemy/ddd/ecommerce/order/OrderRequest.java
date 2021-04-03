package udemy.ddd.ecommerce.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String id;
    private String customerId;
    private List<String> productIds;
    private List<String> couponIds;
    private List<String> promotionIds;
    private String paymentOptionId;
}
