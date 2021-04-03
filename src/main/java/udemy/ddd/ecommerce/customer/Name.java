package udemy.ddd.ecommerce.customer;

import lombok.Data;

@Data
public class Name {
    private String prefix;
    private String firrstName;
    private String middleName;
    private String lastName;
    private String suffix;
}
