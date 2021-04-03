package udemy.ddd.ecommerce.order.event;

import udemy.ddd.ecommerce.order.event.processor.AddressChangeProcessor;
import udemy.ddd.ecommerce.order.event.processor.EventProcessor;

public class EventProcessorFactory {
    public EventProcessor getEventProcessorByEventType(final String type) {
        EventProcessor eventProcessor = null;

        if ("addressChange".equals(type)) {
            eventProcessor = new AddressChangeProcessor();
        }

        return eventProcessor;
    }
}
