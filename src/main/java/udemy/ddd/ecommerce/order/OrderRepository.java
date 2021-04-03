package udemy.ddd.ecommerce.order;

import java.util.List;

public interface OrderRepository {

    public String writeOrder(final Order order);

    public List<Order> fetchOrdersByCustomerIdAndStatus(final String customerId, final String status);
}
