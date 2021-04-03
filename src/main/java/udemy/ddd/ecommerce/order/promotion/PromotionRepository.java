package udemy.ddd.ecommerce.order.promotion;

import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository {
    public Promotion getPromotionById(final String id);
}
