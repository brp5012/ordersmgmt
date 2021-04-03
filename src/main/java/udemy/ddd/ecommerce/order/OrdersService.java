package udemy.ddd.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import udemy.ddd.ecommerce.billing.BillingClientService;
import udemy.ddd.ecommerce.billing.Invoice;
import udemy.ddd.ecommerce.fulfillment.FulfillmentClientService;
import udemy.ddd.ecommerce.order.event.Event;
import udemy.ddd.ecommerce.order.event.EventProcessorFactory;
import udemy.ddd.ecommerce.order.event.processor.EventProcessor;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EventProcessorFactory eventProcessorFactory;

    @Autowired
    private BillingClientService billingClientService;

    @Autowired
    private FulfillmentClientService fulfillmentClientService;

    public void submitOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setProductIds(orderRequest.getProductIds());
        order.setPromotionIds(orderRequest.getPromotionIds());
        order.setPaymentOptionId(orderRequest.getPaymentOptionId());

        String orderId = this.orderRepository.writeOrder(order);

        BigDecimal paymentTotal = order.calculateTotalCost();

        Invoice invoice = new Invoice();
        invoice.setCustomerId(order.getCustomerId());
        invoice.setOrderId(orderId);
        invoice.setTotalCost(paymentTotal);
        this.billingClientService.submitInvoiceForCollection(invoice);

        udemy.ddd.ecommerce.fulfillment.Invoice fulfillmentInvoice = new udemy.ddd.ecommerce.fulfillment.Invoice();
        fulfillmentInvoice.setCustomerId(order.getCustomerId());
        fulfillmentInvoice.setOrderId(orderId);
        fulfillmentInvoice.setType("Submission");
        this.fulfillmentClientService.submitInvoiceForFulfillment(fulfillmentInvoice);
    }

    @GetMapping("/orders")
    public OrdersRespnse getOrderHistory(String customerId, String status) {
        List<Order> orders = this.orderRepository.fetchOrdersByCustomerIdAndStatus(customerId, status);
        OrdersRespnse response = new OrdersRespnse();
        response.setOrders(orders);
        return response;
    }

    /**
     * Batch update of a customer's orders.  This would be better handled through kafka stream publishing an event.
     * @param event
     */
    public void processEvent(Event event) {
        EventProcessor processor = this.eventProcessorFactory.getEventProcessorByEventType(event.getType());
        processor.process(event.getSubjectId());
    }
}
