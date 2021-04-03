package udemy.ddd.ecommerce.order;

import lombok.Data;

import java.util.List;

@Data
public class OrdersRespnse {
    public List<Order> orders;
}
