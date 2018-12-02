package ch.datatrans.bbwtechtalk.util;

/**
 * @author dominik.mengelt@gmail.com
 */
public class Payment {

    private final String transactionId;
    private final String refno;
    private final String paymentMethod;

    public Payment(String transactionId, String refno, String paymentMethod) {
        this.transactionId = transactionId;
        this.refno = refno;
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getRefno() {
        return refno;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
