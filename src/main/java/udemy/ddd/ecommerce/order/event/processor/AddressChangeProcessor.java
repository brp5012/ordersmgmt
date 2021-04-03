package udemy.ddd.ecommerce.order.event.processor;

import org.springframework.beans.factory.annotation.Autowired;
import udemy.ddd.ecommerce.fulfillment.FulfillmentClientService;
import udemy.ddd.ecommerce.fulfillment.Invoice;
import udemy.ddd.ecommerce.order.Order;
import udemy.ddd.ecommerce.order.OrderRepository;

import java.util.List;

public class AddressChangeProcessor implements EventProcessor {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FulfillmentClientService fulfillmentClientService;

    public void process(final String customerId) {
        List<Order> orders = this.orderRepository.fetchOrdersByCustomerIdAndStatus(customerId, "PROCESSING");
        for (Order order : orders) {
            Invoice invoice = new Invoice();
            invoice.setCustomerId(order.getCustomerId());
            invoice.setOrderId(order.getId());
            invoice.setType("Update");
            this.fulfillmentClientService.submitInvoiceForFulfillment(invoice);
        }
    }
}
