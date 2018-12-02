package ch.datatrans.bbwtechtalk.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
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
import java.util.UUID;

/**
 * @author dominik.mengelt@gmail.com
 */
@Entity
@Table(name = "purchase")
public class Purchase {

    private static final String ID = "id";
    private static final String REFNO = "name";
    private static final String TRANSACTION_ID = "transactionId";
    private static final String AMOUNT = "amount";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = REFNO)
    private UUID refno;

    @Column(name = TRANSACTION_ID)
    private Long transactionId; // Datatrans transactionId

    @Column(name = AMOUNT)
    private BigDecimal amount;

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

    public UUID getRefno() {
        return refno;
    }

    public void setRefno(UUID refno) {
        this.refno = refno;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
