package ch.datatrans.bbwteachtalk.controller;

import ch.datatrans.bbwteachtalk.service.PurchaseService;
import ch.datatrans.bbwteachtalk.util.Payment;
import ch.datatrans.bbwteachtalk.util.WebhookPayloadParser;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author dominik.mengelt@gmail.com
 */
@Controller
@RequestMapping("/payment")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping()
    public ModelAndView pay(@Valid Basket basket) {
        String paymentId = purchaseService.initializePurchase(basket);
        return new ModelAndView("redirect:https://pay.sandbox.datatrans.com/v1/start/" + paymentId);
    }

    @GetMapping("/success")
    public String success(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);
        return "success";
    }

    @GetMapping("/cancel")
    public String cancel(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);
        return "cancel";
    }

    @GetMapping("/error")
    public String error(@RequestParam("datatransTrxId") String transactionId, Model model) {
        model.addAttribute("transactionId", transactionId);
        return "error";
    }

    @PostMapping("/listener")
    public void paymentSuccessfulListener(HttpEntity<String> httpEntity) {

        try {
            String payload = httpEntity.getBody();

            if(WebhookPayloadParser.paymentWasSuccessful(payload)) {
                Payment paymentDetails = WebhookPayloadParser.getPaymentDetails(payload);
                purchaseService.updatePurchase(paymentDetails.getRefno(),
                        paymentDetails.getTransactionId(),
                        paymentDetails.getPaymentMethod());

            }
        } catch (Exception e) {
            throw new RuntimeException("Unable to parse Webhook payload. Aborting.");
        }

    }

}
