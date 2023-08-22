package services.payment;

public class PaymentServiceImp implements PaymentService {

    @Override
    public Boolean bayar(Double totalOrder, Double uangCustomer) {
        Boolean paymentStatus;

        if (totalOrder > uangCustomer) {
            paymentStatus = false;
        } else {
            paymentStatus = true;
        }

        return paymentStatus;
    }

}
