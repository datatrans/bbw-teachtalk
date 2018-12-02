package ch.datatrans.bbwtechtalk.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dominik.mengelt@gmail.com
 */
public final class Basket {

    @NotNull
    private final Long articleId;

    @Min(1)
    private final int quantity;

    public Basket(Long articleId, int quantity) {
        this.articleId = articleId;
        this.quantity = quantity;
    }

    public Long getArticleId() {
        return articleId;
    }

    public int getQuantity() {
        return quantity;
    }
}
