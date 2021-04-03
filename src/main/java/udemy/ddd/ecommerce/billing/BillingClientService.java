package udemy.ddd.ecommerce.billing;

public interface BillingClientService {
    public void submitInvoiceForCollection(final Invoice invoice);
}
