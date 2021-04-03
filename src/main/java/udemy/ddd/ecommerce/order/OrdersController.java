package udemy.ddd.ecommerce.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import udemy.ddd.ecommerce.order.event.Event;

import java.util.Optional;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/orders/order")
    public ResponseEntity submitOrder(@RequestBody OrderRequest order) {
        this.ordersService.submitOrder(order);
        return ResponseEntity.of(Optional.empty());
    }

    @GetMapping("/orders")
    public ResponseEntity<OrdersRespnse> getOrderHistory(@RequestParam("customerId") String customerId, @RequestParam String status) {
        Optional body = Optional.of(this.ordersService.getOrderHistory(customerId, status));
        return ResponseEntity.of(body);
    }

    /**
     * Batch update of a customer's orders.  This would be better handled through kafka stream publishing an event.
     * @param event
     */
    @PostMapping("/orders/event")
    public ResponseEntity processEvent(@RequestBody Event event) {
        this.ordersService.processEvent(event);
        return ResponseEntity.of(Optional.empty());
    }
}
