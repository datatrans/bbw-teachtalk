package ch.datatrans.bbwteachtalk.client;

/**
 * @author dominik.mengelt@gmail.com
 */
public final class InitTransactionResponse {

    private String paymentId;

    public InitTransactionResponse() {}

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
