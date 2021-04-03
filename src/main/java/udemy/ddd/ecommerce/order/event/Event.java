package udemy.ddd.ecommerce.order.event;

import lombok.Data;

@Data
public class Event {
    private String subjectId;
    private String type;
    private String timestamp;
}
