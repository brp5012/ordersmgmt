package udemy.ddd.ecommerce.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public interface CustomerClientService {

    public Customer getCustomerById(final String customerId);

}
