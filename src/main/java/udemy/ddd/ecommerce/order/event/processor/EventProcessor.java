package udemy.ddd.ecommerce.order.event.processor;

public interface EventProcessor {

    public void process(final String subjectId);

}
