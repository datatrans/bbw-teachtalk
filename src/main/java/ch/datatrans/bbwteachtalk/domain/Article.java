package ch.datatrans.bbwteachtalk.domain;

/**
 * @author dominik.mengelt@gmail.com
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "article")
public class Article {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOGO = "logo";
    private static final String PRICE = "price";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = ID)
    private Long id;

    @Column(name = NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = LOGO)
    private String logo;

    @Column(name = PRICE)
    private BigDecimal price;

    @ManyToMany(mappedBy = "articles")
    private List<Purchase> purchases = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) &&
                Objects.equals(name, article.name) &&
                Objects.equals(description, article.description) &&
                Objects.equals(logo, article.logo) &&
                Objects.equals(price, article.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, logo, price);
    }
}
