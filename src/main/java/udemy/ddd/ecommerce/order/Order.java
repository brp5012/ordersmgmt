package udemy.ddd.ecommerce.order;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import udemy.ddd.ecommerce.customer.CustomerClientService;
import udemy.ddd.ecommerce.order.promotion.Promotion;
import udemy.ddd.ecommerce.order.promotion.PromotionRepository;
import udemy.ddd.ecommerce.product.Product;
import udemy.ddd.ecommerce.product.ProductClientService;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Order {
    private String id;
    private String customerId;
    private List<String> productIds;
    private List<String> promotionIds;
    private String paymentOptionId;
    private String status;

    @Autowired
    private CustomerClientService customerClientService;

    @Autowired
    private ProductClientService productClientService;

    @Autowired
    private PromotionRepository promotionRepository;

    public BigDecimal calculateTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (String id : this.productIds) {
            Product product = this.productClientService.getProductById(id);
            totalCost.add(product.getCost());
        }
        for (String id : this.promotionIds) {
            Promotion promotion = this.promotionRepository.getPromotionById(id);
            totalCost.subtract(promotion.getValue());
        }
        return totalCost;
    }
}
