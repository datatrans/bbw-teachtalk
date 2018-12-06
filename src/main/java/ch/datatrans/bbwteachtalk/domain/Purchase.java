package ch.datatrans.bbwteachtalk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author dominik.mengelt@gmail.com
 */
@Entity
@Table(name = "purchase")
public class Purchase {

    private static final String ID = "id";
    private static final String REFNO = "refno";
    private static final String TRANSACTION_ID = "transactionId";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_METHOD = "paymentMethod";
    private static final String STATUS = "status";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = REFNO)
    private String refno;

    @Column(name = TRANSACTION_ID)
    private String transactionId; // Datatrans transactionId

    @Column(name = AMOUNT)
    private BigDecimal amount;

    @Column(name = PAYMENT_METHOD)
    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = STATUS)
    private PurchaseState state;

    @ManyToMany
    @JoinTable(
            name = "purchase_article",
            joinColumns={@JoinColumn(name="purchase_id", referencedColumnName=ID)},
            inverseJoinColumns={@JoinColumn(name="article_id", referencedColumnName=ID)})
    private List<Article> articles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public PurchaseState getState() {
        return state;
    }

    public void setState(PurchaseState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return Objects.equals(id, purchase.id) &&
                Objects.equals(refno, purchase.refno) &&
                Objects.equals(transactionId, purchase.transactionId) &&
                Objects.equals(amount, purchase.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refno, transactionId, amount);
    }
}
