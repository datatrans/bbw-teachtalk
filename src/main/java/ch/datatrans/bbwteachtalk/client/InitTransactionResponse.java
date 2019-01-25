package ch.datatrans.bbwteachtalk.client;

/**
 * @author dominik.mengelt@gmail.com
 */
public final class InitTransactionResponse {

    private String transactionId;

    public InitTransactionResponse() {}

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
