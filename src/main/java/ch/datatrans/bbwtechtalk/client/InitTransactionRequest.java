package ch.datatrans.bbwtechtalk.client;

/**
 * @author dominik.mengelt@gmail.com
 */
public final class InitTransactionRequest {

    private final String refno;
    private final Long amount; // The amount of the transaction in the currency’s smallest unit. For example use 1000 for CHF 10.00
    private final String currency;

    public InitTransactionRequest(String refno, Long amount, String currency) {
        this.refno = refno;
        this.amount = amount;
        this.currency = currency;
    }

    public String getRefno() {
        return refno;
    }

    public Long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
