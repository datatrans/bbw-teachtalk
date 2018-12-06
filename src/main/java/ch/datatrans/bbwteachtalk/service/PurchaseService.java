package ch.datatrans.bbwteachtalk.service;

import ch.datatrans.bbwteachtalk.controller.Basket;
import ch.datatrans.bbwteachtalk.client.DatatransClient;
import ch.datatrans.bbwteachtalk.domain.Article;
import ch.datatrans.bbwteachtalk.domain.ArticleRepository;
import ch.datatrans.bbwteachtalk.domain.Purchase;
import ch.datatrans.bbwteachtalk.domain.PurchaseRepository;
import ch.datatrans.bbwteachtalk.domain.PurchaseState;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author dominik.mengelt@gmail.com
 */
@Service
public class PurchaseService {

    private final DatatransClient datatransClient;
    private final PurchaseRepository purchaseRepository;
    private final ArticleRepository articleRepository;


    public PurchaseService(DatatransClient datatransClient,
                           PurchaseRepository purchaseRepository, ArticleRepository articleRepository) {
        this.datatransClient = datatransClient;
        this.purchaseRepository = purchaseRepository;
        this.articleRepository = articleRepository;
    }

    public String initializePurchase(Basket basket) {
        String refno = RandomStringUtils.randomAlphanumeric(20);

        // fetch the articles prices from the DB
        Article article = articleRepository.findById(basket.getArticleId()).orElseThrow(RuntimeException::new);
        BigDecimal price = article.getPrice();

        // Business logic to calculate the amount
        long priceCurrencySmallestUnit = price.scaleByPowerOfTen(price.scale()).longValue() * basket.getQuantity();

        // initialize transaction with Datatrans
        String paymentId = datatransClient.initTransaction(refno, priceCurrencySmallestUnit, "CHF");

        // crate a new purchase in DB
        Purchase purchase = new Purchase();
        purchase.setArticles(List.of(article));
        purchase.setAmount(price.multiply(new BigDecimal(basket.getQuantity())));
        purchase.setRefno(refno);

        // set the state to INITIALIZED
        purchase.setState(PurchaseState.INITIALIZED);
        purchaseRepository.save(purchase);

        return paymentId;
    }

    public void updatePurchase(String refno, String transactionId, String paymentMethod) {
        Purchase purchase = purchaseRepository.findByRefno(refno);

        // the user PAID. set the state to PAID
        purchase.setState(PurchaseState.PAID);
        purchase.setTransactionId(transactionId);
        purchase.setPaymentMethod(paymentMethod);
        purchaseRepository.save(purchase);
    }
}
